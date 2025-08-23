package com.ecommercejob.profils.repo;

import com.ecommercejob.profils.domain.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Optional<Profile> findByEmail(String email);
}
