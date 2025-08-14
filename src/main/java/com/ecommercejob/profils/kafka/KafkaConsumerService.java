package com.ecommercejob.profils.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class KafkaConsumerService {

    @KafkaListener(topics = KafkaTopicsConfig.PROFILS_TOPIC, groupId = "profils-group")
    public void consume(String message) {
        System.out.println("📩 Message reçu depuis Kafka: " + message);
        // TODO: à terme: traiter/mapper/valider/persister
    }
}
