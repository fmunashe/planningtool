package za.co.sabs.planningtool.processor.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.query.LdapQuery;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import za.co.sabs.planningtool.entity.Role;
import za.co.sabs.planningtool.entity.User;
import za.co.sabs.planningtool.processor.AuthenticationProcessor;
import za.co.sabs.planningtool.repository.RoleRepository;
import za.co.sabs.planningtool.repository.UserRepository;
import za.co.sabs.planningtool.service.security.JwtTokenProvider;
import za.co.sabs.planningtool.utils.AppConstants;
import za.co.sabs.planningtool.utils.messages.request.AuthRequest;
import za.co.sabs.planningtool.utils.messages.request.AuthResponse;
import za.co.sabs.planningtool.utils.messages.response.basic.ApiResponse;
import za.co.sabs.planningtool.utils.messages.response.helper.HelperResponse;

import javax.naming.NamingException;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

import static org.springframework.ldap.query.LdapQueryBuilder.query;

@Service
public class AuthenticationProcessorImpl implements AuthenticationProcessor {
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final ObjectMapper objectMapper;
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final LdapTemplate ldapTemplate;
    private final Logger log = LoggerFactory.getLogger(AuthenticationProcessorImpl.class);


    public AuthenticationProcessorImpl(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, ObjectMapper objectMapper, UserRepository userRepository, RoleRepository roleRepository, LdapTemplate ldapTemplate) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.objectMapper = objectMapper;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.ldapTemplate = ldapTemplate;
    }

    @Override
    public ApiResponse<AuthResponse> authenticate(AuthRequest authRequest) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
        );

        try {
            log.info("Authentication success for principal: {} - authorities: {}",
                    auth.getPrincipal(),
                    auth.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList())
            );
            // optional: log full object as json (be careful with credentials)
             log.debug("== Authentication (full) == : {}", objectMapper.writeValueAsString(auth));
        } catch (Exception ignored) { }

        var principal = auth.getPrincipal();
        String username = principal instanceof UserDetails ? ((UserDetails) principal).getUsername() : principal.toString();
        Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
        String token = jwtTokenProvider.generateToken(username, authorities);
        // fetch AD attributes (best-effort) and sync local user
        Map<String, String> adAttrs = fetchAdAttributes(username);
        syncLocalUser(username, principal, authorities, adAttrs);

        Optional<Long> remainingMinutes = extractExpiryInMinutesFromJwt(token);

        AuthResponse response = AuthResponse.builder()
                .token(token)
                .minutesToExpire(remainingMinutes.get())
                .expiresAt(LocalDateTime.now().plusMinutes(remainingMinutes.get()))
                .build();

        return HelperResponse.buildApiResponse(null, null, false, 200, true, AppConstants.SUCCESS_MESSAGE, response);
    }

    private Optional<Long> extractExpiryInMinutesFromJwt(String jwt) {
        try {
            String[] parts = jwt.split("\\.");
            if (parts.length < 2) return Optional.empty();
            String payloadJson = new String(Base64.getUrlDecoder().decode(parts[1]));
            Map<String, Object> claims = objectMapper.readValue(payloadJson, Map.class);
            Object expObj = claims.get("exp");
            if (expObj == null) return Optional.empty();

            long expSeconds;
            if (expObj instanceof Number) {
                expSeconds = ((Number) expObj).longValue();
            } else {
                expSeconds = Long.parseLong(expObj.toString());
            }

            long currentSeconds = Instant.now().getEpochSecond();
            long remainingSeconds = expSeconds - currentSeconds;
            long remainingMinutes = Math.max(0, remainingSeconds / 60); // Convert to minutes and ensure non-negative

            return Optional.of(remainingMinutes);
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    private void syncLocalUser(String username, Object principal, Collection<? extends GrantedAuthority> authorities, Map<String, String> adAttrs) {
        try {
            Set<Role> newRoles = toRoleSet(authorities);

            userRepository.findByUsername(username).ifPresentOrElse(u -> {
                // user exists - update roles if different
                Set<Role> existing = u.getRoles();
                boolean different = (existing == null && newRoles != null && !newRoles.isEmpty())
                        || (existing != null && !existing.equals(newRoles));
                if (different) {
                    u.setRoles(newRoles);
                    userRepository.save(u);
                }
            }, () -> {
                // create minimal user record when missing
                User newUser = new User();
                newUser.setUsername(username);

                // populate AD attributes if available (best-effort; methods must exist on User entity)
                if (adAttrs != null && !adAttrs.isEmpty()) {
                    try { newUser.setEmail(adAttrs.getOrDefault("mail", null)); } catch (NoSuchMethodError ignored) {}
                    try { newUser.setFirstName(adAttrs.getOrDefault("givenName", null)); } catch (NoSuchMethodError ignored) {}
                    try { newUser.setLastName(adAttrs.getOrDefault("sn", null)); } catch (NoSuchMethodError ignored) {}
                }

                newUser.setRoles(newRoles);
                newUser.setEnabled(true);
                newUser.setAccountNonLocked(true);
                newUser.setAccountNonExpired(true);
                userRepository.save(newUser);
            });
        } catch (Exception e) {
            log.debug("=== syncLocalUser failed for === {}: {}", username, e.getMessage());
        }
    }

    private Set<Role> toRoleSet(Collection<? extends GrantedAuthority> authorities) {
        if (authorities == null || authorities.isEmpty()) {
            return Set.of();
        }
        return authorities.stream()
                .map(a -> roleRepository.findByName(a.getAuthority()).orElseGet(() -> {
                    // create missing role record if necessary (persisted roles use exact authority string)
                    Role r = new Role(a.getAuthority());
                    return roleRepository.save(r);
                }))
                .collect(Collectors.toSet());
    }

    private Map<String, String> fetchAdAttributes(String username) {
        try {
            LdapQuery q = query()
                    .where("sAMAccountName").is(username)
                    .or("userPrincipalName").is(username);

            List<Map<String, String>> results = ldapTemplate.search(q, (AttributesMapper<Map<String, String>>) attrs -> {
                Map<String, String> map = new HashMap<>();
                try {
                    // Try to get from attributes first
                    putIfPresent(attrs, "mail", map);
                    putIfPresent(attrs, "displayName", map);
                    putIfPresent(attrs, "givenName", map);
                    putIfPresent(attrs, "sn", map);
                    putIfPresent(attrs, "sAMAccountName", map);
                    putIfPresent(attrs, "userPrincipalName", map);

                    // If we don't have first/last name, try to extract from DN
                    if ((!map.containsKey("givenName") || !map.containsKey("sn")) && attrs.get("distinguishedName") != null) {
                        String dn = attrs.get("distinguishedName").get().toString();
                        extractNamesFromDN(dn, map);
                    }
                } catch (NamingException ignored) {}
                return map;
            });

            return results.isEmpty() ? Collections.emptyMap() : results.get(0);
        } catch (Exception e) {
            log.debug("Failed to read AD attributes for {}: {}", username, e.getMessage());
            return Collections.emptyMap();
        }
    }

    private void extractNamesFromDN(String dn, Map<String, String> map) {
        try {
            // DN format: "CN=Farai Zihove,OU=Contractors,OU=SABS Users,DC=SABS,DC=co,DC=za"
            if (dn.startsWith("CN=")) {
                String cn = dn.substring(3, dn.indexOf(','));
                String[] names = cn.split("\\s+", 2); // Split on first space
                if (names.length >= 1 && !map.containsKey("givenName")) {
                    map.put("givenName", names[0]); // First name
                }
                if (names.length > 1 && !map.containsKey("sn")) {
                    map.put("sn", names[1]); // Last name
                } else if (names.length == 1 && !map.containsKey("sn")) {
                    // If only one name, use it as last name
                    map.put("sn", names[0]);
                }
            }
        } catch (Exception e) {
            log.debug("Failed to extract names from DN: {}", dn, e);
        }
    }

    private void putIfPresent(Attributes attrs, String key, Map<String, String> map) throws NamingException {
        Attribute a = attrs.get(key);
        if (a != null && a.get() != null) {
            map.put(key, a.get().toString());
        }
    }
}
