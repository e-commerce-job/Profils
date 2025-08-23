package com.ecommercejob.profils.api.dev;

import com.ecommercejob.profils.api.dto.ProfileResponse;
import com.ecommercejob.profils.service.ProfileService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

/**
 * ENDPOINTS DEV UNIQUEMENT (profil 'dev')
 * Permet de créer/mettre à jour un profil SANS passer par Kafka,
 * pour tester les endpoints REST rapidement.
 *
 * POST /dev/seed/profile
 */
@Profile("dev")
@RestController
@RequestMapping("/dev/seed")
public class DevSeedController {

    private final ProfileService service;

    public DevSeedController(ProfileService service) {
        this.service = service;
    }

    /**
     * Requête de seed (équivalent d'un event 'user_created' / 'user_updated')
     */
    public static final class SeedProfileRequest {
        @NotNull
        private UUID userId;

        @NotBlank
        private String firstName;

        @NotBlank
        private String lastName;

        @Email @NotBlank
        private String email;

        @NotBlank
        private String role;

        public UUID getUserId() { return userId; }
        public void setUserId(UUID userId) { this.userId = userId; }

        public String getFirstName() { return firstName; }
        public void setFirstName(String firstName) { this.firstName = firstName; }

        public String getLastName() { return lastName; }
        public void setLastName(String lastName) { this.lastName = lastName; }

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getRole() { return role; }
        public void setRole(String role) { this.role = role; }
    }

    /**
     * Crée/met à jour un profil directement en base (sans Kafka).
     *
     * Exemple Postman:
     * POST http://localhost:8080/dev/seed/profile
     * Content-Type: application/json
     * {
     *   "userId": "11111111-1111-1111-1111-111111111111",
     *   "firstName": "Meriem",
     *   "lastName": "Ben Ali",
     *   "email": "meriem@example.com",
     *   "role": "acheteur"
     * }
     */
    @PostMapping("/profile")
    public ProfileResponse seedProfile(@Valid @RequestBody SeedProfileRequest req) {
        // réutilise la même logique que le consumer Kafka
        service.upsertFromUserEvent(
                req.getUserId(),
                req.getFirstName(),
                req.getLastName(),
                req.getEmail(),
                req.getRole()
        );
        // on renvoie l'état actuel du profil
        return service.get(req.getUserId());
    }
}
