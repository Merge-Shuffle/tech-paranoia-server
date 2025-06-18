package org.example.techparanoiaserver.response;

import lombok.Builder;

import java.util.UUID;

@Builder
public record ChangePasswordResponse(
        UUID userId,
        String message
) { }
