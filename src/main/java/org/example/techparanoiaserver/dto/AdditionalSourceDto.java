package org.example.techparanoiaserver.dto;

import lombok.Data;
import org.example.techparanoiaserver.entity.Question.AdditionalSourceType;

@Data
public class AdditionalSourceDto {
    private String url;
    private AdditionalSourceType sourceType;
}
