package com.crediya.api;
import com.crediya.api.dto.ApplicantResponseDTO;
import com.crediya.api.dto.CreateApplicantDTO;
import com.crediya.api.mapper.ApplicantDtoMapper;
import com.crediya.model.applicant.Applicant;
import com.crediya.usecase.registerapplicant.RegisterApplicantUseCase;
import com.crediya.usecase.registerapplicant.ValidationsApplicantUseCase;
import com.crediya.usecase.registerapplicant.exceptions.BusinessExceptions;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Validator;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import org.springframework.web.server.ServerWebInputException;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Handler {

    private final RegisterApplicantUseCase registerApplicantUseCase;
    private final ValidationsApplicantUseCase validationsApplicantUseCase;
    private final ApplicantDtoMapper mapper;
    private final Validator validator;
    private final GlobalExceptionHandler exceptionHandler;
    private static final Logger LOGGER = LoggerFactory.getLogger(RegisterApplicantUseCase.class);


    public Mono<ServerResponse> listenPOSTRegisterApplicant(ServerRequest request) {
        LOGGER.debug("Entering to listenPOSTRegisterApplicant - request: {}", request);
        return request.bodyToMono(CreateApplicantDTO.class)
                .flatMap(dto -> {
                    Set<ConstraintViolation<CreateApplicantDTO>> violations = validator.validate(dto);
                    if (!violations.isEmpty()) {
                        LOGGER.warn("An error will be produced because some field in the CreateApplicantDTO is bad");
                        return Mono.error(new ConstraintViolationException(violations));
                    }
                    Applicant applicant = mapper.toModel(dto);
                    LOGGER.info(" listenPOSTRegisterApplicant - CreateApplicantDTO was mapped to Model: applicant");
                    return registerApplicantUseCase.saveApplicant(applicant);
                })
                .flatMap(saved -> ServerResponse.status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(saved))
                .onErrorResume(ConstraintViolationException.class, exceptionHandler::handleConstraintViolation)
                .onErrorResume(ServerWebInputException.class, exceptionHandler::handleDeserializationException)
                .onErrorResume(BusinessExceptions.class, exceptionHandler::handleBusinessException)
                .onErrorResume(Throwable.class, exceptionHandler::handleGenericException);
    }
    public Mono<ServerResponse> listenGETApplicant(ServerRequest request) {
        LOGGER.debug("Entering to listenGETApplicant - request: {}", request);
        return request.bodyToMono(CreateApplicantDTO.class)
                .flatMap(dto -> {
                    // Mapear DTO a entidad de dominio
                    Applicant applicant = mapper.toModel(dto);
                    LOGGER.info(" listenGETApplicant - CreateApplicantDTO was mapped to Model: applicant");
                    return validationsApplicantUseCase.existingByIdentityDocument(applicant);
                })
                .flatMap( applicant -> {
                    ApplicantResponseDTO responseDTO = new ApplicantResponseDTO(
                        applicant.getIdentityDocument(),
                        applicant.getName());
                    LOGGER.info(" listenGETApplicant - ApplicantResponseDTO was mapped from Model: applicant");
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .bodyValue(responseDTO);

                })
                .onErrorResume(e ->ServerResponse.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .bodyValue("Error"+ e.getMessage()));


    }
}

