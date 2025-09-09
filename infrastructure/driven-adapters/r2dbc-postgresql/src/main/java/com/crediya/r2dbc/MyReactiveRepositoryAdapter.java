package com.crediya.r2dbc;

import com.crediya.model.applicant.Applicant;
import com.crediya.model.applicant.gateways.ApplicantRepository;
import com.crediya.r2dbc.data.ApplicantData;
import com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        Applicant,
        ApplicantData,
        String,
        MyReactiveRepository
> implements ApplicantRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyReactiveRepositoryAdapter.class);

    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, Applicant.class/* change for domain model */));
    }

    @Override
    public Mono<Applicant> saveApplicant(Applicant applicant) {
        LOGGER.debug("Entering to saveApplicant method - applicant: {}", applicant);
        return repository.save(mapper.map(applicant, ApplicantData.class))
                .map(data -> mapper.map(data, Applicant.class));
    }

    @Override
    public Mono<Applicant> findByEmail(String email) {
        LOGGER.debug("Entering to findByEmail method - email: {}" , email);
        return repository.findByEmail(email)
                .map(data->mapper.map(data, Applicant.class));
    }

    @Override
    public Mono<Applicant> findByIdentityDocument(String identityDocument) {
        LOGGER.debug("Entering to findByIdentityDocument method - identityDocument: {}", identityDocument);
        return repository.findByIdentityDocument(identityDocument)
                .map(data -> mapper.map(data, Applicant.class));
    }
}
