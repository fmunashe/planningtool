package za.co.sabs.planningtool.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.mapping.SimpleAuthorityMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.ldap.DefaultSpringSecurityContextSource;
import org.springframework.security.ldap.authentication.BindAuthenticator;
import org.springframework.security.ldap.authentication.LdapAuthenticationProvider;
import org.springframework.security.ldap.userdetails.DefaultLdapAuthoritiesPopulator;
import org.springframework.security.web.SecurityFilterChain;
import za.co.sabs.planningtool.service.security.JwtAuthenticationFilter;

import java.util.List;

@Configuration
public class SecurityConfig {

    private final JwtAuthenticationFilter jwtAuthFilter;

    public SecurityConfig(JwtAuthenticationFilter jwtAuthFilter) {
        this.jwtAuthFilter = jwtAuthFilter;
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
        bindAuthenticator.setUserDnPatterns(new String[]{"uid={0},ou=people"}); // adapt to your LDAP layout

        DefaultLdapAuthoritiesPopulator authoritiesPopulator =
                new DefaultLdapAuthoritiesPopulator(contextSource, "ou=groups");
        authoritiesPopulator.setGroupRoleAttribute("cn");
        authoritiesPopulator.setGroupSearchFilter("(member={0})");
        authoritiesPopulator.setRolePrefix("ROLE_");

        LdapAuthenticationProvider provider = new LdapAuthenticationProvider(bindAuthenticator, authoritiesPopulator);
        // map LDAP roles directly
        var mapper = new SimpleAuthorityMapper();
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
        http
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/planningtool/auth/**").permitAll()
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthFilter, org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter.class)
                .sessionManagement(sm -> sm.sessionCreationPolicy(org.springframework.security.config.http.SessionCreationPolicy.STATELESS));
        return http.build();
    }
}
