package com.crediya.passwordhashingadapter;

import com.crediya.model.User.User;
import com.crediya.model.authresult.gateways.GenerateTokenPort;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.Map;

@Component
public class JwtAdapter implements GenerateTokenPort {

    private final JwtUtil jwtUtil;

    public JwtAdapter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<String> generateToken(User user) {
        return Mono.fromCallable(() -> {
            Map<String, Object> claims = new HashMap<>();
            claims.put("role", user.getRole());
            claims.put("email", user.getEmail());
            return jwtUtil.generateToken(user.getEmail(), claims);
        });
    }
}
