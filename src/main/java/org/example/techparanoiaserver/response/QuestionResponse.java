package org.example.techparanoiaserver.response;

import lombok.Builder;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;

import java.util.Set;
import java.util.UUID;

@Builder
public record QuestionResponse(
        UUID questionId,
        String title,
        QuestionDifficulty difficulty,
        Category category,
        Set<String> tags
) { }
