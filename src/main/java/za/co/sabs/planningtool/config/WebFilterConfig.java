package za.co.sabs.planningtool.config;

import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.security.core.userdetails.UserDetailsService;
import za.co.sabs.planningtool.repository.UserRepository;
import za.co.sabs.planningtool.service.security.JwtAuthenticationFilter;
import za.co.sabs.planningtool.service.security.JwtTokenProvider;

@Configuration
public class WebFilterConfig {

    // Autowire the dependencies your JwtAuthenticationFilter needs
    @Bean
    public FilterRegistrationBean<Filter> jwtAuthenticationFilterRegistration(JwtTokenProvider tokenProvider, UserRepository userRepository) {
        JwtAuthenticationFilter filter = new JwtAuthenticationFilter(tokenProvider, userRepository);

        FilterRegistrationBean<Filter> reg = new FilterRegistrationBean<>(filter);
        reg.setName("jwtAuthenticationFilter");
        reg.addUrlPatterns("/*");
        reg.setOrder(Ordered.HIGHEST_PRECEDENCE + 10);
        return reg;
    }
}
