package com.ecommercejob.profils.api.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.URL;

public record ProfileUpdateRequest(
        @Size(max = 20, message = "Téléphone trop long (max 20)")
        String phone,

        @Size(max = 500, message = "Bio trop longue (max 500)")
        String bio,

        @URL(message = "photoUrl doit être une URL valide")
        String photoUrl
) {}
