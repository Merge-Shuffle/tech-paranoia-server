package org.example.techparanoiaserver.service.userquestion;

import org.springframework.security.core.Authentication;

import java.util.UUID;

public interface UserQuestionService {
    String addQuestionToUser(UUID questionId, Authentication connectedUser);
}
