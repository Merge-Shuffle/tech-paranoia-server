package org.example.techparanoiaserver.exception;

import org.example.techparanoiaserver.entity.Question.Category;

import java.util.UUID;

public class QuestionTitleInUseException extends RuntimeException {
    public QuestionTitleInUseException(String title, Category category, UUID questionId) {
        super(title.toUpperCase() + " already used in " + category + " category for question " + questionId.toString());
    }
}
