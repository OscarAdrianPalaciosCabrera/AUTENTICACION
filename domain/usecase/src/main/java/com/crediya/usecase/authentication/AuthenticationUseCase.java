package com.crediya.usecase.authentication;

import com.crediya.model.User.User;
import com.crediya.model.User.gateways.UserRepository;
import com.crediya.model.authresult.AuthResult;
import com.crediya.model.authresult.gateways.AuthResultRepository;
import com.crediya.model.authresult.gateways.GenerateTokenPort;
import com.crediya.model.authresult.gateways.PasswordHashingPort;
import com.crediya.usecase.registeruser.exceptions.BusinessExceptions;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@RequiredArgsConstructor
public class AuthenticationUseCase implements AuthResultRepository {

    private final UserRepository userRepository;
    private final PasswordHashingPort hashingPort;
    private final GenerateTokenPort generateTokenPort;
    private static final Logger LOGGER = Logger.getLogger(AuthenticationUseCase.class.getName());

    @Override
    public Mono<AuthResult> authenticate(String email, String rawPassword) {
        LOGGER.info("Entering to authenticate method");
        return userRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new BusinessExceptions("Credenciales invalidas")))
                .flatMap(user -> {
                    if (!hashingPort.matches(rawPassword, user.getPasswordHash())) {
                        LOGGER.info("Obtein hash" + user.getPasswordHash());
                        LOGGER.warning("Password don´t matches");
                        return Mono.error(new BusinessExceptions("Credenciales invalidas"));
                    }
                    return generateToken(user)
                            .map(token -> new AuthResult(user.getEmail(), user.getRole(), token));
                });
    }

    @Override
    public Mono<String> generateToken(User user) {
        LOGGER.info("Entering to generateToken method");
        return generateTokenPort.generateToken(user);
    }
}
