package org.example.techparanoiaserver.response;

import lombok.Builder;
import org.example.techparanoiaserver.common.PageResponse;

import java.time.LocalDate;
import java.util.UUID;

@Builder
public record UserResponse(
        UUID userId,
        String firstName,
        String lastName,
        LocalDate dateOfBirth,
        boolean accountLocked,
        boolean accountEnabled,
        PageResponse<QuestionResponse> questionList
) {
}
