package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;

import java.util.List;
import java.util.Set;

@Data
public class QuestionCreateRequest {
    @NotNull(message = "Title can not be null")
    @NotBlank(message = "Title can not be blank")
    private String title;
    @NotNull(message = "Difficulty can not be null")
    private QuestionDifficulty difficulty;
    @NotNull(message = "Category can not be null")
    private Category category;
    @NotNull(message = "Content can not be null")
    @NotBlank(message = "Title can not be blank")
    private String content;
    private Set<String> tags;
    private List<AdditionalSourceDto> additionalSources;
}
