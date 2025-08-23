package com.ecommercejob.profils.service;

import com.ecommercejob.profils.api.dto.ProfileResponse;
import com.ecommercejob.profils.api.dto.ProfileUpdateRequest;
import com.ecommercejob.profils.domain.Profile;
import com.ecommercejob.profils.repo.ProfileRepository;
import com.ecommercejob.profils.service.exceptions.NotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class ProfileService {

    private final ProfileRepository repo;

    public ProfileService(ProfileRepository repo) {
        this.repo = repo;
    }

    @Transactional(readOnly = true)
    public ProfileResponse get(UUID userId) {
        Profile p = repo.findById(userId).orElseThrow(() ->
                new NotFoundException("Profil introuvable: " + userId));
        return toResponse(p);
    }

    @Transactional
    public ProfileResponse update(UUID userId, ProfileUpdateRequest req) {
        Profile p = repo.findById(userId).orElseThrow(() ->
                new NotFoundException("Profil introuvable: " + userId));
        if (req.photoUrl() != null) p.setPhotoUrl(req.photoUrl());
        if (req.phone() != null) p.setPhone(req.phone());
        if (req.bio() != null) p.setBio(req.bio());
        return toResponse(repo.save(p));
    }

    // utilisé par Kafka consumer pour créer/mettre à jour (réplication depuis Utilisateurs)
    @Transactional
    public void upsertFromUserEvent(UUID userId, String firstName, String lastName, String email, String role) {
        Profile p = repo.findById(userId).orElseGet(() -> {
            Profile np = new Profile();
            np.setUserId(userId);
            return np;
        });
        if (firstName != null) p.setFirstName(firstName);
        if (lastName  != null) p.setLastName(lastName);
        if (email     != null) p.setEmail(email);
        if (role      != null) p.setRole(role);
        repo.save(p);
    }

    private ProfileResponse toResponse(Profile p) {
        ProfileResponse r = new ProfileResponse();
        r.userId = p.getUserId();
        r.firstName = p.getFirstName();
        r.lastName = p.getLastName();
        r.email = p.getEmail();
        r.role = p.getRole();
        r.photoUrl = p.getPhotoUrl();
        r.phone = p.getPhone();
        r.bio = p.getBio();
        r.verified = p.isVerified();
        r.createdAt = p.getCreatedAt();
        r.updatedAt = p.getUpdatedAt();
        return r;
    }
}
