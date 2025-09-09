package com.crediya.usecase.registerapplicant;

import com.crediya.model.applicant.Applicant;
import com.crediya.model.applicant.gateways.ApplicantRepository;
import com.crediya.usecase.registerapplicant.exceptions.BusinessExceptions;
import lombok.AllArgsConstructor;
import reactor.core.publisher.Mono;

import java.util.logging.Logger;

@AllArgsConstructor
public class ValidationsApplicantUseCase {

    private static final Logger LOGGER = Logger.getLogger(RegisterApplicantUseCase.class.getName());
    private final ApplicantRepository applicantRepository;


    Mono<Void> existingValidation(Applicant applicant){
        LOGGER.info("Entering to existingValidation method - Mono");
        return applicantRepository.findByEmail(applicant.getEmail())
                .hasElement()
                .flatMap(exists -> exists ? Mono.error(new BusinessExceptions("Applicant already exists"))
                        :Mono.empty()
                );
    }

    public Mono<Applicant> existingByIdentityDocument(Applicant applicant){
        LOGGER.info("Entering to existingByIdentityDocument method - Mono");
        return applicantRepository.findByIdentityDocument(applicant.getIdentityDocument());

    }

    Mono<Void> salaryValidation(Applicant applicant) {
        LOGGER.info("Entering to salaryValidation method - Mono");
        return applicant.isSalaryValid() ? Mono.empty()
                :Mono.error(new BusinessExceptions("Invalid salary"));
    }
}
