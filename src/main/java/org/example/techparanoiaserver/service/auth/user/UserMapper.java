package org.example.techparanoiaserver.service.auth.user;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.common.PageResponse;
import org.example.techparanoiaserver.entity.Question.QuestionMapper;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.entity.user.UserQuestion;
import org.example.techparanoiaserver.repository.UserQuestionRepository;
import org.example.techparanoiaserver.response.RegisterResponse;
import org.example.techparanoiaserver.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserMapper {

    private final QuestionMapper questionMapper;
    private final UserQuestionRepository userQuestionRepository;

    public RegisterResponse toRegisterResponse(User user){
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .build();
    }

    public UserResponse toResponse(User user, Page<UserQuestion> userQuestions){
        return UserResponse.builder()
                .userId(user.getId())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .dateOfBirth(user.getDateOfBirth())
                .accountEnabled(user.getEnabled())
                .accountLocked(user.getAccountLocked())
                .questionList(new PageResponse<>(
                            userQuestions.stream()
                                        .map(userQuestion -> questionMapper.toResponse(userQuestion.getQuestion()))
                                        .toList(),
                            userQuestions.getNumber(),
                            userQuestions.getSize(),
                            userQuestions.getTotalElements(),
                            userQuestions.getTotalPages(),
                            userQuestions.isFirst(),
                            userQuestions.isLast()
                        )
                )
                .build();
    }
}
