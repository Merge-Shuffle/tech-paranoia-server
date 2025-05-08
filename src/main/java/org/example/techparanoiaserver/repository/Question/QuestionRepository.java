package org.example.techparanoiaserver.repository.Question;

import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface QuestionRepository extends JpaRepository<Question, UUID> {
    Optional<Question> getQuestionById(UUID id);

    @Query(value = "select * from questions", nativeQuery = true)
    List<Question> findAll();

    List<Question> findAllByCategory(Category category);
}
