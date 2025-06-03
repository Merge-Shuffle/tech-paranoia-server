package org.example.techparanoiaserver.entity.Question;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionMapper {

    private final AdditionalSourceMapper additionalSourceMapper;

    public Question toQuestion(QuestionCreateRequest request){
        Question question = new Question();
        return getQuestion(request, question);
    }

    public Question updateProperties(QuestionCreateRequest src, Question dst){
        return getQuestion(src, dst);
    }

    private Question getQuestion(QuestionCreateRequest src, Question dst) {
        dst.setTags(src.getTags());
        dst.setTitle(src.getTitle());
        dst.setContent(src.getContent());
        dst.setCategory(src.getCategory());
        dst.setDifficulty(src.getDifficulty());

        List<AdditionalSource> mappedSources =
                additionalSourceMapper.toAdditionalSourceList(src.getAdditionalSources(), dst);

        dst.setAdditionalSources(mappedSources);

        return dst;
    }
}
