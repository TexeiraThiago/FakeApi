package com.feignclientapi.demo.infrastructure.message.consumer;

import com.feignclientapi.demo.apiv1.dto.ProductDTO;
import com.feignclientapi.demo.business.service.ProductService;
import com.feignclientapi.demo.infrastructure.exception.BusinessException;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class FakeApiConsumer {

    private final ProductService service;

    @KafkaListener(topics = "${topic.fake-api.consumer.name}", groupId = "${topic.fake-api.consumer.group-id}")
    public void consumerProducerProducts(ProductDTO productDTO) {
        try {
            service.saveOneProductDTO(productDTO);
        } catch (Exception e) {
            throw new BusinessException("Erro during consume Kafka message"+ e);
        }
    }
}
