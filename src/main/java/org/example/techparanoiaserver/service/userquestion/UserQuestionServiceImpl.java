package org.example.techparanoiaserver.service.userquestion;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.entity.user.UserQuestion;
import org.example.techparanoiaserver.repository.UserQuestionRepository;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserQuestionServiceImpl implements UserQuestionService{

    private final UserQuestionRepository repository;
    private final QuestionService questionService;

    @Override
    public String addQuestionToUser(UUID questionId, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Question question = questionService.getQuestionById(questionId);

        UserQuestion userQuestion = UserQuestion.builder()
                .question(question)
                .user(user)
                .addedAt(LocalDateTime.now())
                .build();

        return repository.save(userQuestion).getId().toString();
    }
}
