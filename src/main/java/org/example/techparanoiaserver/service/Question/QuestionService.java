package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.common.PageResponse;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.response.QuestionResponse;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question getQuestionById(UUID id);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionByCategory(Category category);
    Question createQuestion(QuestionCreateRequest request);
    Question deleteQuestion(UUID id);
    Question updateQuestion(UUID id, QuestionCreateRequest request);
    PageResponse<QuestionResponse> getQuestionsByUser(User user);
}
