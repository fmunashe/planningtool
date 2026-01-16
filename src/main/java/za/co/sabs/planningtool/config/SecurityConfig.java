package za.co.sabs.planningtool.config;

import jakarta.servlet.Filter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;
import za.co.sabs.planningtool.repository.UserRepository;
import za.co.sabs.planningtool.service.security.JwtAuthenticationFilter;
import za.co.sabs.planningtool.service.security.JwtTokenProvider;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtTokenProvider tokenProvider;
    private final UserRepository userRepository;

    public SecurityConfig(JwtTokenProvider tokenProvider, UserRepository userRepository) {
        this.tokenProvider = tokenProvider;
        this.userRepository = userRepository;
    }

    @Bean
    public DefaultSpringSecurityContextSource contextSource(@Value("${spring.ldap.urls}") String ldapUrl,
                                                            @Value("${spring.ldap.base}") String ldapBase,
                                                            @Value("${spring.ldap.username}") String managerDn,
                                                            @Value("${spring.ldap.password}") String managerPw) {
        var cs = new DefaultSpringSecurityContextSource(List.of(ldapUrl), ldapBase);
        cs.setUserDn(managerDn);
        cs.setPassword(managerPw);
        return cs;
    }

    @Bean
    public AuthenticationProvider ldapAuthProvider(DefaultSpringSecurityContextSource contextSource) {
        BindAuthenticator bindAuthenticator = new BindAuthenticator(contextSource);
        bindAuthenticator.setUserSearch(new org.springframework.security.ldap.search.FilterBasedLdapUserSearch(
                "", // search base relative to your LDAP base DN
                "(sAMAccountName={0})",
                contextSource
        ));

        DefaultLdapAuthoritiesPopulator authoritiesPopulator =
                new DefaultLdapAuthoritiesPopulator(
                        contextSource,
                        null
                );

        authoritiesPopulator.setGroupRoleAttribute("cn");
        authoritiesPopulator.setGroupSearchFilter("(member={0})");
        authoritiesPopulator.setRolePrefix("ROLE_");

        LdapAuthenticationProvider provider =
                new LdapAuthenticationProvider(bindAuthenticator, authoritiesPopulator);

        SimpleAuthorityMapper mapper = new SimpleAuthorityMapper();
        mapper.setConvertToUpperCase(true);
        provider.setAuthoritiesMapper(mapper);

        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationProvider ldapAuthProvider) {
        return new ProviderManager(ldapAuthProvider);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        Filter jwtFilter = new JwtAuthenticationFilter(tokenProvider, userRepository);
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(
                                "/auth/**",
                                "/planningtool/v3/api-docs/**",
                                "/planningtool/v3/api-docs.yaml",
                                "/planningtool/swagger-ui/**",
                                "/planningtool/swagger-ui.html",
                                "/planningtool/swagger-ui/index.html",
                                "/planningtool/swagger-resources/**",
                                "/planningtool/webjars/**",

                                "/v3/api-docs/**",
                                "/v3/api-docs.yaml",
                                "/swagger-ui/**",
                                "/swagger-ui.html",
                                "/swagger-ui/index.html",
                                "/swagger-resources/**",
                                "/webjars/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm -> sm.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
