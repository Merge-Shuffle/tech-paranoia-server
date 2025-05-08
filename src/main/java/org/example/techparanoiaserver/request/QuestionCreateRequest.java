package org.example.techparanoiaserver.request;

import lombok.Data;
import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;

import java.util.List;
import java.util.Set;

@Data
public class QuestionCreateRequest {
    private String title;
    private QuestionDifficulty difficulty;
    private String content;
    private Set<String> tags;
    private List<AdditionalSourceDto> additionalSources;
}
