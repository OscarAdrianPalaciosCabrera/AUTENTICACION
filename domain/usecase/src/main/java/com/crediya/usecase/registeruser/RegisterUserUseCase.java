package com.crediya.usecase.registeruser;

import com.crediya.model.User.User;
import com.crediya.model.User.gateways.UserRepository;
//import com.crediya.model.Usercreatedevent.UserCreatedEvent;
//import com.crediya.model.Usercreatedevent.gateways.UserCreatedEventRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


import java.util.logging.Logger;

@RequiredArgsConstructor
public class RegisterUserUseCase {

    private static final Logger LOGGER = Logger.getLogger(RegisterUserUseCase.class.getName());
    private final UserRepository UserRepository;
    private final ValidationsUserUseCase validationsUserUseCase;
    //private final UserCreatedEventRepository UserCreatedEventRepository;

    public Mono<User> saveUser (User User){
        LOGGER.info("Entering to saveUser method - Mono");
        return validationsUserUseCase.existingValidation(User)
                .then(validationsUserUseCase.salaryValidation(User))
                .then(UserRepository.saveUser(User));
                /*.flatMap(saved -> {
                    UserCreatedEvent event = new UserCreatedEvent(
                            saved.getIdentityDocument()
                    );
                    return  UserCreatedEventRepository.publishUserCreated(event)
                            .thenReturn(saved);

                });*/
    }



}