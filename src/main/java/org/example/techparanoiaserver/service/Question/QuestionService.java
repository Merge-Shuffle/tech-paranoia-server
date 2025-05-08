package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.request.QuestionCreateRequest;

import java.util.List;
import java.util.UUID;

public interface QuestionService {
    Question getQuestionById(UUID id);
    List<Question> getAllQuestions();
    List<Question> getAllQuestionByCategory(Category category);
    Question createQuestion(QuestionCreateRequest request);
    Question deleteQuestion(UUID id);
    Question updateQuestion(UUID id, QuestionCreateRequest request);
}
