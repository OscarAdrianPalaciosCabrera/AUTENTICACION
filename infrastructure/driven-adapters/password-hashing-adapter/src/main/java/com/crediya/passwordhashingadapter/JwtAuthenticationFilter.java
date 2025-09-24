package com.crediya.passwordhashingadapter;

import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

import java.util.List;


@AllArgsConstructor
public class JwtAuthenticationFilter implements WebFilter {

    private final JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);
            try {
                Claims claims = jwtUtil.validateToken(token);
                String role = claims.get("role").toString();

                List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + getRoleName(role)));

                Authentication auth = new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
                return chain.filter(exchange)
                        .contextWrite(ReactiveSecurityContextHolder.withAuthentication(auth));
            } catch (Exception e) {
                return Mono.error(new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Invalid JWT"));
            }
        }
        return chain.filter(exchange);
    }

    private String getRoleName(String role) {
        return switch (role) {
            case "1" -> "ADMINISTRATOR";
            case "2" -> "APPLICANT";
            case "3" -> "ADVISOR";
            default -> "UNKNOWN";
        };
    }
}
