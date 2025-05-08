package org.example.techparanoiaserver.repository.Question;

import org.example.techparanoiaserver.entity.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> getQuestionById(UUID id);
}
