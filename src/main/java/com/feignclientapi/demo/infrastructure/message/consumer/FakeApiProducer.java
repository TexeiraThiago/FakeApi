package com.feignclientapi.demo.infrastructure.message.consumer;

import com.feignclientapi.demo.infrastructure.exception.BusinessException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class FakeApiProducer {

    @Value("${topic.fake-api.producer.name}")
    private String topic;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public FakeApiProducer(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }


    public void sendResponse(final String message) {
        try {
            kafkaTemplate.send(topic, message);
        }catch (Exception e) {
            throw new BusinessException("Error when produce message");
        }
    }
}
