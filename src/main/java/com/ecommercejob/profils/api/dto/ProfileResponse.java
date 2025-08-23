package com.ecommercejob.profils.api.dto;

import java.time.OffsetDateTime;
import java.util.UUID;

public class ProfileResponse {
    public UUID userId;
    public String firstName;
    public String lastName;
    public String email;
    public String role;
    public String photoUrl;
    public String phone;
    public String bio;
    public boolean verified;
    public OffsetDateTime createdAt;
    public OffsetDateTime updatedAt;
}
