package com.example.demo.config;

import com.example.demo.jwt.JwtConverter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityBeans {
    private final JwtConverter customConverter;
    private static final String ADMIN = "admin";
    private static final String USER = "user";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(c -> c.disable())
                .authorizeHttpRequests(cfg -> cfg
                        .requestMatchers("/api/home").permitAll()
                        .requestMatchers("/api/home/admin/**").hasRole(ADMIN)
                        .requestMatchers("/api/home/user/**").hasRole(USER)
                        .requestMatchers("/api/home/admin-user/**").hasAnyRole(ADMIN, USER)
                )
                .oauth2ResourceServer(auth -> auth.jwt(jwt -> jwt.jwtAuthenticationConverter(customConverter)))
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .build();
    }
}
