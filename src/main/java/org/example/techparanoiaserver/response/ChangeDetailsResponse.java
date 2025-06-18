package org.example.techparanoiaserver.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ChangeDetailsResponse(
        UUID userId,
        String email,
        String firstName,
        String lastName,
        String dateOfBirth
) {
}
