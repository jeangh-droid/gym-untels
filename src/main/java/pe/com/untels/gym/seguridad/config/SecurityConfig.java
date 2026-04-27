package pe.com.untels.gym.seguridad.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) {
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/auth/**")
                .permitAll())
                .csrf(csrf -> csrf.disable())
                .httpBasic(basic -> basic.disable());
        return http.build();
    }
}
