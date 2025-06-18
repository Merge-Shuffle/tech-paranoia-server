package org.example.techparanoiaserver.response;

import lombok.Builder;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record ChangeDetailsResponse(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        LocalDate dateOfBirth
) {
}
