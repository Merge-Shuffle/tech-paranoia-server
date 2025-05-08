package org.example.techparanoiaserver.entity.Question;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "questions")
@Data
public class Question {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "uuid", updatable = false, nullable = false)
    private UUID id;

    @Column(nullable = false)
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private QuestionDifficulty difficulty;

    @Column(nullable = false)
    private String content;

    @ElementCollection
    @CollectionTable(
            name = "question_tags",
            joinColumns = @JoinColumn(name = "question_id")
    )
    @Column(name = "tag")
    private Set<String> tags;


    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AdditionalSource> additionalSources;

}
