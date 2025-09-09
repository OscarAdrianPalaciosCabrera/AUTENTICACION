package com.crediya.model.applicant.gateways;

import com.crediya.model.applicant.Applicant;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ApplicantRepository {

    Mono<Applicant> saveApplicant (Applicant applicant);
    Mono<Applicant> findByEmail (String email);
    Mono<Applicant> findByIdentityDocument(String identityDocument);
    Flux<Applicant> findAll();
}
