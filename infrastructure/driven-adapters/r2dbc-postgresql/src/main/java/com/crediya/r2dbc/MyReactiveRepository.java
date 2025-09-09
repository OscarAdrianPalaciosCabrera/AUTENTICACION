package com.crediya.r2dbc;

import com.crediya.r2dbc.data.ApplicantData;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

// TODO: This file is just an example, you should delete or modify it
public interface MyReactiveRepository extends ReactiveCrudRepository<ApplicantData, String>, ReactiveQueryByExampleExecutor<ApplicantData> {
    Mono<ApplicantData> findByEmail(String email);
    Mono<ApplicantData> findByIdentityDocument(String identityDocument);
}
