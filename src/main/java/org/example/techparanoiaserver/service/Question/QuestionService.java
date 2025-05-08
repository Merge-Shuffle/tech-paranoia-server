package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.entity.Question.Question;

import java.util.UUID;

public interface QuestionService {
    Question getQuestionById(UUID id);
}
