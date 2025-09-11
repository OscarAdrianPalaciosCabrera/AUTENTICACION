package com.crediya.model.authresult.gateways;

import com.crediya.model.User.User;
import reactor.core.publisher.Mono;

public interface GenerateTokenPort {
    Mono<String> generateToken(User user);
}
