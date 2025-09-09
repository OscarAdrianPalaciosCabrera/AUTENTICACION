package com.crediya.usecase.registerapplicant;

import com.crediya.model.applicant.Applicant;
import com.crediya.model.applicant.gateways.ApplicantRepository;
//import com.crediya.model.applicantcreatedevent.ApplicantCreatedEvent;
//import com.crediya.model.applicantcreatedevent.gateways.ApplicantCreatedEventRepository;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;


import java.util.logging.Logger;

@RequiredArgsConstructor
public class RegisterApplicantUseCase {

    private static final Logger LOGGER = Logger.getLogger(RegisterApplicantUseCase.class.getName());
    private final ApplicantRepository applicantRepository;
    private final ValidationsApplicantUseCase validationsApplicantUseCase;
    //private final ApplicantCreatedEventRepository applicantCreatedEventRepository;

    public Mono<Applicant> saveApplicant (Applicant applicant){
        LOGGER.info("Entering to saveApplicant method - Mono");
        return validationsApplicantUseCase.existingValidation(applicant)
                .then(validationsApplicantUseCase.salaryValidation(applicant))
                .then(applicantRepository.saveApplicant(applicant));
                /*.flatMap(saved -> {
                    ApplicantCreatedEvent event = new ApplicantCreatedEvent(
                            saved.getIdentityDocument()
                    );
                    return  applicantCreatedEventRepository.publishApplicantCreated(event)
                            .thenReturn(saved);

                });*/
    }



}
