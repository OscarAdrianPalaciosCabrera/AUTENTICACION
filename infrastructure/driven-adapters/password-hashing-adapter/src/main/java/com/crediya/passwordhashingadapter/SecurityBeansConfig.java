package com.crediya.passwordhashingadapter;

import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.SecurityWebFiltersOrder;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityBeansConfig {

    private static final Logger LOGGER = LoggerFactory.getLogger(SecurityBeansConfig.class);


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http, JwtUtil jwtUtil) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/v1/login").permitAll()
                        .pathMatchers("/api/v1/usuarios/existingByIdentityDocument").permitAll()
                        .pathMatchers("/api/v1/usuarios").hasAnyRole("ADMINISTRATOR", "ADVISOR")
                        .anyExchange().authenticated()
                )
                .addFilterAt(new JwtAuthenticationFilter(jwtUtil), SecurityWebFiltersOrder.AUTHENTICATION)
                .build();
    }

    @PostConstruct
    public void init() {
        LOGGER.info("✅ SecurityBeansConfig cargada!");
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
