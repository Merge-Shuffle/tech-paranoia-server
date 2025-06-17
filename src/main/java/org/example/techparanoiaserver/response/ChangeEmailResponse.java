package org.example.techparanoiaserver.response;

import lombok.Builder;

@Builder
public record ChangeEmailResponse(
        String jwt
) { }
