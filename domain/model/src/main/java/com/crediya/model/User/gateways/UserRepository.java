package com.crediya.model.User.gateways;

import com.crediya.model.User.User;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface UserRepository {

    Mono<User> saveUser (User User);
    Mono<User> findByEmail (String email);
    Mono<User> findByIdentityDocument(String identityDocument);
    Flux<User> findAll();
}
