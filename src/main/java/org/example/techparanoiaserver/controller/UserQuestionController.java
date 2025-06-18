package org.example.techparanoiaserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.service.userquestion.UserQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/api/user_questions")
@RequiredArgsConstructor
public class UserQuestionController {

    private final UserQuestionService service;

    @PatchMapping("/{question-id}")
    public ResponseEntity<?> addQuestionToUserList(
                @PathVariable("question-id") UUID questionId,
                Authentication connectedUser
    ) {
            return ResponseEntity.ok(service.addQuestionToUser(questionId, connectedUser));
    }
}
