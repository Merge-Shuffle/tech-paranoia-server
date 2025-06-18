package org.example.techparanoiaserver.service.userquestion;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface UserQuestionService {
    UUID addQuestionToUser(UUID questionId, Authentication connectedUser);

    UUID deleteQuestionFromUser(UUID questionId, Authentication connectedUser);
}
