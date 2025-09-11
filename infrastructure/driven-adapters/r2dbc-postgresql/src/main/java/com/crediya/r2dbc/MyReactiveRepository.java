package com.crediya.r2dbc;

import com.crediya.r2dbc.data.UserData;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveRepository extends ReactiveCrudRepository<UserData, String>, ReactiveQueryByExampleExecutor<UserData> {
    Mono<UserData> findByEmail(String email);
    Mono<UserData> findByIdentityDocument(String identityDocument);
}
