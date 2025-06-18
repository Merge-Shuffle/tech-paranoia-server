package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.common.PageResponse;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.Question.QuestionMapper;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.example.techparanoiaserver.exception.QuestionTitleInUseException;
import org.example.techparanoiaserver.repository.Question.QuestionRepository;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.response.QuestionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
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

        Optional<Question> questionOptional =
                questionRepository.findByTitleAndCategory(request.getTitle(), request.getCategory());

        if (questionOptional.isPresent()) {
            throw new QuestionTitleInUseException(request.getTitle(), request.getCategory(), questionOptional.get().getId());
        }

        Question newQuestion = questionMapper.toQuestion(request);
        return questionRepository.save(newQuestion);
    }

    @Override
    public Question updateQuestion(UUID id, QuestionCreateRequest request) {
        Question question = getQuestionById(id);
        question = questionMapper.updateProperties(request, question);
        return questionRepository.save(question);
    }

    @Override
    public PageResponse<QuestionResponse> getQuestionsByUser(User user) {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Question> questions = questionRepository.findQuestionByUserId(user.getId(), pageable);
        return null;
    }


}
