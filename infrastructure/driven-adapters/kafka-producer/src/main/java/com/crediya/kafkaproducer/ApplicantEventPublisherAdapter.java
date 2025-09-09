/*package com.crediya.kafkaproducer;

import com.crediya.model.applicantcreatedevent.ApplicantCreatedEvent;
import com.crediya.model.applicantcreatedevent.gateways.ApplicantCreatedEventRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import reactor.kafka.sender.KafkaSender;
import reactor.kafka.sender.SenderRecord;

@Component
@RequiredArgsConstructor
public class ApplicantEventPublisherAdapter implements ApplicantCreatedEventRepository {

    private final KafkaSender<String, ApplicantCreatedEvent> kafkaSender;

    @Override
    public Mono<Void> publishApplicantCreated(ApplicantCreatedEvent event) {
        SenderRecord<String, ApplicantCreatedEvent, Void> record =
                SenderRecord.create(new ProducerRecord<>("applicant-created-topic", event.getIdentityDocumentApplicant(), event), null);

        return kafkaSender.send(Mono.just(record))
                .next()
                .doOnNext(result -> {
                    if(result.exception() != null){
                        throw new RuntimeException("Error sending kafka event", result.exception());
                    }
                })
                .then();
    }
}*/
