package org.example.techparanoiaserver.service.Question;

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
        return List.of();
    }

    @Override
    public Question createQuestion(QuestionCreateRequest request) {
        return null;
    }

    @Override
    public Question deleteQuestion(UUID id) {
        Question deletedQuestion = getQuestionById(id);
        questionRepository.delete(deletedQuestion);
        return deletedQuestion;
    }

    @Override
    public Question updateQuestion(QuestionCreateRequest request) {
        return null;
    }
}
