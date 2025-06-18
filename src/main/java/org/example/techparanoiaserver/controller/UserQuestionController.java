package org.example.techparanoiaserver.controller;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.service.userquestion.UserQuestionService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/user_questions")
@RequiredArgsConstructor
public class UserQuestionController {

    private final UserQuestionService service;

    @PatchMapping("/{question-id}")
    public ResponseEntity<UUID> addQuestionToUserList(
                @PathVariable("question-id") UUID questionId,
                Authentication connectedUser
    ) {
            return ResponseEntity.ok(service.addQuestionToUser(questionId, connectedUser));
    }

    @DeleteMapping("/{question-id}")
    public ResponseEntity<UUID> deleteQuestionFromUserList(
            @PathVariable("question-id") UUID questionId,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(service.deleteQuestionFromUser(questionId, connectedUser));
    }
}
