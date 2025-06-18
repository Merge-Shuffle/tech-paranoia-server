package org.example.techparanoiaserver.repository;

import org.example.techparanoiaserver.entity.user.UserQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.UUID;

public interface UserQuestionRepository extends JpaRepository<UserQuestion, UUID> {
    @Query(
            """
                SELECT userQuestion
                FROM UserQuestion userQuestion
                WHERE userQuestion.user.id=:userId
            """
    )
    Page<UserQuestion> findAllByUserId(UUID userId, Pageable pageable);
}
