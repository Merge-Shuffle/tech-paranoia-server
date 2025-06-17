package org.example.techparanoiaserver.entity.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.example.techparanoiaserver.entity.Question.Question;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "user_question")
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class UserQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "question_id")
    @JsonIgnore
    private Question question;

    private LocalDateTime addedAt = LocalDateTime.now();

}
