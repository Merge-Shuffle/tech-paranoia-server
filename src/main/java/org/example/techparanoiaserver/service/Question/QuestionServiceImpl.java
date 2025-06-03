package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.Question.QuestionMapper;
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
    private final QuestionMapper questionMapper;

    @Autowired
    public QuestionServiceImpl(
            QuestionRepository questionRepository,
            QuestionMapper questionMapper
                               ){
        this.questionRepository = questionRepository;
        this.questionMapper = questionMapper;
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
        Question newQuestion = questionMapper.toQuestion(request);
        return questionRepository.save(newQuestion);
    }

    @Override
    public Question updateQuestion(UUID id, QuestionCreateRequest request) {
        Question question = getQuestionById(id);
        question = questionMapper.updateProperties(request, question);
        return questionRepository.save(question);
    }


}
