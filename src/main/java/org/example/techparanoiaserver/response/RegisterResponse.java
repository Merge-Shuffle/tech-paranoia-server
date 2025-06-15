package org.example.techparanoiaserver.response;

import lombok.Builder;
import org.example.techparanoiaserver.entity.user.role.Role;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Builder
public record RegisterResponse(
        UUID id,
        String email,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        List<Role> roles
) { }
