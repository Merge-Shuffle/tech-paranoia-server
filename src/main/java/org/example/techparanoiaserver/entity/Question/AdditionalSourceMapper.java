package org.example.techparanoiaserver.entity.Question;

import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AdditionalSourceMapper {

    public List<AdditionalSource> toAdditionalSourceList(List<AdditionalSourceDto> list, Question question){
        if (list == null){
            return new ArrayList<>();
        }

        return list.stream()
                .map(e -> toAdditionalSource(e, question))
                .toList();
    }
    public AdditionalSource toAdditionalSource(AdditionalSourceDto additionalSourceDto, Question question){
        AdditionalSource additionalSource = new AdditionalSource();
        additionalSource.setSourceType(additionalSourceDto.getSourceType());
        additionalSource.setUrl(additionalSourceDto.getUrl());
        additionalSource.setQuestion(question);
        return additionalSource;
    }
}
