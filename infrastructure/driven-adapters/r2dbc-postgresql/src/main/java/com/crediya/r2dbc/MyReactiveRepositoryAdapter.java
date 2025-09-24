package com.crediya.r2dbc;

import com.crediya.model.User.User;
import com.crediya.model.User.gateways.UserRepository;
import com.crediya.r2dbc.data.UserData;
import com.crediya.r2dbc.helper.ReactiveAdapterOperations;
import org.reactivecommons.utils.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public class MyReactiveRepositoryAdapter extends ReactiveAdapterOperations<
        User,
        UserData,
        String,
        MyReactiveRepository
> implements UserRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(MyReactiveRepositoryAdapter.class);

    public MyReactiveRepositoryAdapter(MyReactiveRepository repository, ObjectMapper mapper) {
        super(repository, mapper, d -> mapper.map(d, User.class/* change for domain model */));
    }

    @Override
    public Mono<User> saveUser(User User) {
        LOGGER.debug("Entering to saveUser method - User: {}", User);
        return save(User)
                .map(data -> mapper.map(data, User.class));
    }

    @Override
    public Mono<User> findByEmail(String email) {
        LOGGER.debug("Entering to findByEmail method - email: {}" , email);
        return repository.findByEmail(email)
                .map(data->mapper.map(data, User.class));
    }

    @Override
    public Mono<User> findByIdentityDocument(String identityDocument) {
        LOGGER.debug("Entering to findByIdentityDocument method - identityDocument: {}", identityDocument);
        return repository.findByIdentityDocument(identityDocument)
                .map(data -> mapper.map(data, User.class));
    }
}
