package com.crediya.usecase.registeruser;
import com.crediya.model.User.User;
import com.crediya.model.User.gateways.UserRepository;
import com.crediya.usecase.registeruser.exceptions.BusinessExceptions;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;
import java.util.logging.Logger;

@AllArgsConstructor
public class ValidationsUserUseCase {

    private static final Logger LOGGER = Logger.getLogger(RegisterUserUseCase.class.getName());
    private final UserRepository UserRepository;


    Mono<Void> existingValidation(User User){
        LOGGER.info("Entering to existingValidation method - Mono");
        return UserRepository.findByEmail(User.getEmail())
                .hasElement()
                .flatMap(exists -> exists ? Mono.error(new BusinessExceptions("User already exists"))
                        :Mono.empty()
                );
    }

    public Mono<User> findByIdentityDocument(String identityDocument){
        LOGGER.info("Entering to existingByIdentityDocument method - Mono");
        return UserRepository.findByIdentityDocument(identityDocument);

    }

    Mono<Void> salaryValidation(User User) {
        LOGGER.info("Entering to salaryValidation method - Mono");
        return User.isSalaryValid() ? Mono.empty()
                :Mono.error(new BusinessExceptions("Invalid salary"));
    }
}