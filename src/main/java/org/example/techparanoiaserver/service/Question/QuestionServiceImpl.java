package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.example.techparanoiaserver.repository.Question.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class QuestionServiceImpl implements QuestionService{

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public Question getQuestionById(UUID id) {

        Optional<Question> questionOptional =
                questionRepository.getQuestionById(id);

        if (questionOptional.isEmpty()){
            throw new NoQuestionMatchingIdFoundException(id);
        }

        return questionOptional.get();
    }
}
