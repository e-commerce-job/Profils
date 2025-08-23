package com.ecommercejob.profils.api;

import com.ecommercejob.profils.api.dto.ProfileResponse;
import com.ecommercejob.profils.api.dto.ProfileUpdateRequest;
import com.ecommercejob.profils.service.ProfileService;

import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/profiles")
public class ProfilesController {

    private final ProfileService service;

    public ProfilesController(ProfileService service) {
        this.service = service;
    }

    @GetMapping("/{userId}")
    public ProfileResponse get(@PathVariable UUID userId) {
        return service.get(userId);
    }

    @PutMapping("/{userId}")
    public ProfileResponse update(@PathVariable UUID userId,
                                  @Valid @RequestBody ProfileUpdateRequest req) {
        return service.update(userId, req);
    }
}
