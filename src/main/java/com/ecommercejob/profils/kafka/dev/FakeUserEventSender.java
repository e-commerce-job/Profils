package com.ecommercejob.profils.kafka.dev;

import com.ecommercejob.profils.kafka.KafkaTopics;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
@Profile("dev")
public class FakeUserEventSender implements ApplicationRunner {

    private final KafkaTemplate<String, String> kafka;

    public FakeUserEventSender(KafkaTemplate<String, String> kafka) {
        this.kafka = kafka;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        UUID userId = UUID.randomUUID();
        String payload = """
      {"type":"user_created","userId":"%s","firstName":"Meriem","lastName":"Ben Ali","email":"meriem@example.com","role":"acheteur"}
      """.formatted(userId);
        kafka.send(KafkaTopics.USERS_EVENTS, userId.toString(), payload);
        System.out.println("🚀 Fake user_created envoyé pour userId=" + userId);
    }
}
