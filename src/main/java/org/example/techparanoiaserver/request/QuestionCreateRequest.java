package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;

import java.util.List;
import java.util.Set;

@Data
public class QuestionCreateRequest {
    @NotNull
    private String title;
    @NotNull
    private QuestionDifficulty difficulty;
    @NotNull
    private Category category;
    @NotNull
    private String content;
    private Set<String> tags;
    private List<AdditionalSourceDto> additionalSources;
}
