package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.AdditionalSource;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.example.techparanoiaserver.repository.Question.QuestionRepository;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService{

    private final QuestionRepository questionRepository;

    @Autowired
    public QuestionServiceImpl(QuestionRepository questionRepository){
        this.questionRepository = questionRepository;
    }

    @Override
    public Question getQuestionById(UUID id) {

        Optional<Question> questionOptional =
                questionRepository.getQuestionById(id);

        if (questionOptional.isEmpty()){
            throw new NoQuestionMatchingIdFoundException(id);
        }

        return questionOptional.get();
    }

    @Override
    public List<Question> getAllQuestions() {
        return questionRepository.findAll();
    }

    @Override
    public List<Question> getAllQuestionByCategory(Category category) {
        return questionRepository.findAllByCategory(category);
    }

    @Override
    public Question deleteQuestion(UUID id) {
        Question deletedQuestion = getQuestionById(id);
        questionRepository.delete(deletedQuestion);
        return deletedQuestion;
    }

    @Override
    public Question createQuestion(QuestionCreateRequest request) {
        Question newQuestion = new Question();
        assignValuesFromRequest(newQuestion, request);
        return questionRepository.save(newQuestion);
    }

    private void assignValuesFromRequest(Question question, QuestionCreateRequest request){
        question.setTags(request.getTags());
        question.setTitle(request.getTitle());
        question.setContent(request.getContent());
        question.setCategory(request.getCategory());
        question.setDifficulty(request.getDifficulty());

        List<AdditionalSource> mappedSources =
                mapToAdditionalSourcesList(request.getAdditionalSources(), question);

        question.setAdditionalSources(mappedSources);
    }

    private List<AdditionalSource> mapToAdditionalSourcesList(List<AdditionalSourceDto> list, Question question){
        return list.stream()
                .map(e -> mapToAdditionalSource(e, question))
                .toList();
    }

    private AdditionalSource mapToAdditionalSource(AdditionalSourceDto additionalSourceDto, Question question){
        AdditionalSource additionalSource = new AdditionalSource();
        additionalSource.setSourceType(additionalSourceDto.getSourceType());
        additionalSource.setUrl(additionalSourceDto.getUrl());
        additionalSource.setQuestion(question);
        return additionalSource;
    }

    @Override
    public Question updateQuestion(UUID id, QuestionCreateRequest request) {
        Question question = getQuestionById(id);
        assignValuesFromRequest(question, request);
        return questionRepository.save(question);
    }


}
