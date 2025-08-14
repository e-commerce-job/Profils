package com.ecommercejob.profils.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class KafkaProducerService {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public KafkaProducerService(KafkaTemplate<String, String> kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void send(String message) {
        kafkaTemplate.send(KafkaTopicsConfig.PROFILS_TOPIC, message);
        System.out.println("📤 Message envoyé vers Kafka: " + message);
    }
}
