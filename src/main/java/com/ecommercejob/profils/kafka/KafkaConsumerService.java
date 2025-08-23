package com.ecommercejob.profils.kafka;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ecommercejob.profils.service.ProfileService;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class KafkaConsumerService {

    private final ObjectMapper mapper;
    private final ProfileService profiles;

    public KafkaConsumerService(ObjectMapper mapper, ProfileService profiles) {
        this.mapper = mapper;
        this.profiles = profiles;
    }

    // Consomme les événements du microservice Utilisateurs
    @KafkaListener(topics = KafkaTopics.USERS_EVENTS, groupId = "profils-group")
    public void consumeUsersEvents(String raw) throws Exception {
        JsonNode json = mapper.readTree(raw);
        String type = json.path("type").asText();

        if ("user_created".equals(type) || "user_updated".equals(type) || "role_changed".equals(type)) {
            UUID userId = UUID.fromString(json.path("userId").asText());
            String first = json.path("firstName").asText(null);
            String last  = json.path("lastName").asText(null);
            String email = json.path("email").asText(null);
            String role  = json.path("role").asText(null);
            profiles.upsertFromUserEvent(userId, first, last, email, role);
            System.out.println("📥 Event " + type + " appliqué pour userId=" + userId);
        }
        // TODO: gérer user_deleted, badge_earned... plus tard
    }
}
