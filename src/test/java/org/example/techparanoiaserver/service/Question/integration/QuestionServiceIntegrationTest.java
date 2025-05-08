package org.example.techparanoiaserver.service.Question.integration;

import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.AdditionalSourceType;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.Set;
import java.util.UUID;

@SpringBootTest
@ActiveProfiles("test")
public class QuestionServiceIntegrationTest {

    @Autowired
    private QuestionService questionService;

    @Test
    void testCreateQuestion_whenNullValueProvided_throwException(){
        //Arrange
        QuestionCreateRequest request = new QuestionCreateRequest();
        request.setTags(Set.of("hibernate", "junit", "maven"));
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setDifficulty(QuestionDifficulty.MEDIUM);

        AdditionalSourceDto additionalSourceDto = new AdditionalSourceDto();
        additionalSourceDto.setSourceType(AdditionalSourceType.YOUTUBE);
        additionalSourceDto.setUrl("test.url");
        request.setAdditionalSources(List.of(additionalSourceDto));

        //Act & Assert

        Assertions.assertThrows(DataIntegrityViolationException.class, () -> questionService.createQuestion(request));
    }

    @Test
    void testDeleteQuestion_whenValidIdProvided_returnDeletedQuestion(){
        //Arrange
        QuestionCreateRequest request = new QuestionCreateRequest();
        request.setTags(Set.of("hibernate", "junit", "maven"));
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setDifficulty(QuestionDifficulty.MEDIUM);

        AdditionalSourceDto additionalSourceDto = new AdditionalSourceDto();
        additionalSourceDto.setSourceType(AdditionalSourceType.YOUTUBE);
        additionalSourceDto.setUrl("test.url");
        request.setAdditionalSources(List.of(additionalSourceDto));

        request.setCategory(Category.JAVA);

        Question createdQuestion =
                questionService.createQuestion(request);

        UUID id = createdQuestion.getId();

        //Act & Assert

        Question deletedQuestion =
                questionService.deleteQuestion(id);

        Assertions.assertNotNull(deletedQuestion);
        Assertions.assertEquals(deletedQuestion.getId(), createdQuestion.getId());
    }
}
