package com.crediya.api;
import com.crediya.api.dto.*;
import com.crediya.api.mapper.UserDtoMapper;
import com.crediya.model.User.User;
import com.crediya.usecase.authentication.AuthenticationUseCase;
import com.crediya.usecase.registeruser.RegisterUserUseCase;
import com.crediya.usecase.registeruser.ValidationsUserUseCase;
import com.crediya.usecase.registeruser.exceptions.BusinessExceptions;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.web.server.ServerWebInputException;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Handler {

    private static final Logger LOGGER = LoggerFactory.getLogger(Handler.class);

    private final RegisterUserUseCase registerUserUseCase;
    private final ValidationsUserUseCase validationsUserUseCase;
    private final UserDtoMapper mapper;
    private final AuthenticationUseCase authenticationUseCase;
    private final Validator validator;
    private final GlobalExceptionHandler exceptionHandler;



    public Mono<ServerResponse> listenPOSTRegisterUser(ServerRequest request) {
        LOGGER.debug("Entering to listenPOSTRegisterUser - request: {}", request);

        return ReactiveSecurityContextHolder.getContext()
                .flatMap(ctx -> {
                    Authentication auth = ctx.getAuthentication();

                    // Validar que el usuario tenga el rol ADMIN o ADVISOR
                    boolean hasRole = auth.getAuthorities().stream()
                            .anyMatch(a -> a.getAuthority().equals("ROLE_ADMINISTRATOR") || a.getAuthority().equals("ROLE_ADVISOR"));

                    if (!hasRole) {
                        LOGGER.warn("User {} attempted to access register endpoint without proper role", auth.getName());
                        return ServerResponse.status(HttpStatus.FORBIDDEN)
                                .bodyValue("Access denied: insufficient role");
                    }

                    // Lógica de registro de usuario
                    return request.bodyToMono(CreateUserDTO.class)
                            .flatMap(dto -> {
                                Set<ConstraintViolation<CreateUserDTO>> violations = validator.validate(dto);
                                if (!violations.isEmpty()) {
                                    LOGGER.warn("Validation error in CreateUserDTO");
                                    return Mono.error(new ConstraintViolationException(violations));
                                }

                                User user = mapper.toModel(dto);
                                LOGGER.info("CreateUserDTO was mapped to Model: User");
                                return registerUserUseCase.saveUser(user);
                            })
                            .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED)
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .bodyValue(saved))
                            .onErrorResume(ConstraintViolationException.class, exceptionHandler::handleConstraintViolation)
                            .onErrorResume(ServerWebInputException.class, exceptionHandler::handleDeserializationException)
                            .onErrorResume(BusinessExceptions.class, exceptionHandler::handleBusinessException)
                            .onErrorResume(Throwable.class, exceptionHandler::handleGenericException);
                });
    }

    public Mono<ServerResponse> listenGETUser(ServerRequest request) {
        LOGGER.debug("Entering to listenGETUser - request: {}", request);
        return request.bodyToMono(UserDTO.class)
                .flatMap(dto -> {
                    String identityDocument = dto.identityDocumentApplicant();
                    LOGGER.info("identityDocument: " + identityDocument);
                    return validationsUserUseCase.findByIdentityDocument(identityDocument)
                            .flatMap( user -> {
                                LOGGER.info("Entro");
                                UserResponseDTO responseDTO = new UserResponseDTO(user.getName(),
                                        user.getEmail(),
                                        user.getIdentityDocument(),
                                        user.getRole(),
                                        user.getBaseSalary());
                                LOGGER.info(" listenGETUser - UserResponseDTO was mapped from Model: User - dto: {}", responseDTO);
                                return ServerResponse.ok()
                                        .contentType(MediaType.APPLICATION_JSON)
                                        .bodyValue(responseDTO);
                            })
                            .switchIfEmpty(
                                    Mono.error(new BusinessExceptions("User with identity document " + identityDocument + " not found")));
                })
                .onErrorResume(ConstraintViolationException.class, exceptionHandler::handleConstraintViolation)
                .onErrorResume(ServerWebInputException.class, exceptionHandler::handleDeserializationException)
                .onErrorResume(BusinessExceptions.class, exceptionHandler::handleBusinessException)
                .onErrorResume(Throwable.class, exceptionHandler::handleGenericException);
                /*.onErrorResume(e ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error"+ e.getMessage()));*/


    }
    public Mono<ServerResponse> listenPOSTLogin(ServerRequest request) {
        LOGGER.debug("Entering to listenPOSTLogin - serverRequest: {}", request);

        return request.bodyToMono(LoginRequestDTO.class)
                .flatMap(dto -> {
                    Set<ConstraintViolation<LoginRequestDTO>> violations = validator.validate(dto);
                    if (!violations.isEmpty()) {
                        LOGGER.warn("Validation error in LoginRequestDto");
                        return Mono.error(new ConstraintViolationException(violations));
                    }
                    LOGGER.info("listenPOSTLogin - Valid LoginRequestDto received for user: {}", dto.email());
                    return authenticationUseCase.authenticate(dto.email(), dto.password())
                            .map(authResult -> new LoginResponseDTO(
                                    authResult.getToken(),
                                    "Bearer",
                                    3600L,
                                    authResult.getEmail(),
                                    authResult.getRole()
                            ));
                })
                .flatMap(response -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(response))
                .onErrorResume(ConstraintViolationException.class, exceptionHandler::handleConstraintViolation)
                .onErrorResume(ServerWebInputException.class, exceptionHandler::handleDeserializationException)
                .onErrorResume(BusinessExceptions.class, exceptionHandler::handleBusinessException)
                .onErrorResume(Throwable.class, exceptionHandler::handleGenericException);
    }

}

