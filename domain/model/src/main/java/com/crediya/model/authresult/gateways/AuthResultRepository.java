package com.crediya.model.authresult.gateways;

import com.crediya.model.User.User;
import com.crediya.model.authresult.AuthResult;
import reactor.core.publisher.Mono;

public interface AuthResultRepository {

    Mono<AuthResult> authenticate(String email, String password);
    Mono<String> generateToken(User user);
}
