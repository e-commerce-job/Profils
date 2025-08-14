package com.ecommercejob.profils.kafka;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicsConfig {

    public static final String PROFILS_TOPIC = "profils-topic";

    @Bean
    public NewTopic profilsTopic() {
        return TopicBuilder.name(PROFILS_TOPIC)
                .partitions(1)
                .replicas(1)
                .build();
    }
}
