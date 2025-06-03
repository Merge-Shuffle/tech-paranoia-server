package org.example.techparanoiaserver.service.Question.integration;

import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.AdditionalSourceType;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;
import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.example.techparanoiaserver.repository.Question.QuestionRepository;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@ActiveProfiles("test")
@TestInstance(TestInstance.Lifecycle.PER_METHOD)
public class QuestionServiceIntegrationTest {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private QuestionRepository questionRepository;

    private QuestionCreateRequest request;

    @BeforeEach
    void setup(){
        request = new QuestionCreateRequest();
        request.setTags(Set.of("hibernate", "junit", "maven"));
        request.setTitle("Test Title");
        request.setContent("Test Content");
        request.setDifficulty(QuestionDifficulty.MEDIUM);

        AdditionalSourceDto additionalSourceDto = new AdditionalSourceDto();
        additionalSourceDto.setSourceType(AdditionalSourceType.YOUTUBE);
        additionalSourceDto.setUrl("test.url");
        request.setAdditionalSources(List.of(additionalSourceDto));

        //Clean DB after each test
        questionRepository.deleteAll();
    }

    @Test
    void testGetAllQuestions_returnList(){
        //Arrange
        List<Question> questionList =
                new ArrayList<>(
                        Stream.generate(Question::new)
                                .map(this::populateObject)
                                .limit(10)
                                .toList()
                );

        questionRepository.saveAll(questionList);

        //Action
        List<Question> result = questionService.getAllQuestions();

        //Assert
        Assertions.assertFalse(result.isEmpty());
        Assertions.assertEquals(result.size(), questionList.size());

    }

    @Test
    void testCreateQuestion_whenNullValueProvided_throwException(){
        //Act & Assert
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> questionService.createQuestion(request));
    }

    @Test
    void testCreateQuestion_returnSavedQuestion(){
        //Arrange
        request.setCategory(Category.JAVA);

        //Act & Assert
        Question savedQuestion =
                questionService.createQuestion(request);

        Question questionFromDb =
                questionService.getQuestionById(savedQuestion.getId());

        Assertions.assertNotNull(savedQuestion);
        Assertions.assertNotNull(questionFromDb);
        assertThat(savedQuestion)
                .usingRecursiveComparison()
                .ignoringCollectionOrderInFields("tags", "additionalSources")
                .isEqualTo(questionFromDb);
    }

    @Test
    void testDeleteQuestion_whenValidIdProvided_returnDeletedQuestion(){
        //Arrange
        request.setCategory(Category.JAVA);

        Question createdQuestion =
                questionService.createQuestion(request);

        UUID id = createdQuestion.getId();

        //Act & Assert

        Question deletedQuestion =
                questionService.deleteQuestion(id);

        Assertions.assertNotNull(deletedQuestion);
        Assertions.assertEquals(deletedQuestion.getId(), createdQuestion.getId());
        Assertions.assertThrows(NoQuestionMatchingIdFoundException.class, () -> questionService.getQuestionById(id));
    }



    @Test
    @DisplayName("Update question test")
    void testUpdateQuestion_returnUpdatedQuestion(){
        //Arrange
        request.setCategory(Category.JAVA);

        Question createdQuestion =
                questionService.createQuestion(request);

        UUID id = createdQuestion.getId();

        request.setTitle("UPDATED_TITLE");
        request.setTags(null);

        //Action

        questionService.updateQuestion(id, request);

        Question updatedQuestion =
                questionService.getQuestionById(id);

        //Assert

        Assertions.assertNotNull(updatedQuestion);
        Assertions.assertNotEquals(createdQuestion, updatedQuestion);
        Assertions.assertEquals(updatedQuestion.getTitle(), request.getTitle());
        Assertions.assertTrue(updatedQuestion.getTags().isEmpty());
    }

    @Test
    @DisplayName("Update question with null property")
    void testUpdateQuestion_whenNullPropertyProvided_throwException(){
        //Arrange
        request.setCategory(Category.JAVA);

        Question createdQuestion =
                questionService.createQuestion(request);

        UUID id = createdQuestion.getId();

        request.setTitle("UPDATED_TITLE");
        request.setTags(null);
        request.setCategory(null);

        //Action && Assert
        Assertions.assertThrows(DataIntegrityViolationException.class, () -> questionService.updateQuestion(id, request));
    }


    @Test
    void testGetQuestionById_whenValidIdProvided_returnQuestion(){
        request.setCategory(Category.JAVA);

        Question createdQuestion =
                questionService.createQuestion(request);

        UUID id = createdQuestion.getId();

        Question questionFromDb =
                questionService.getQuestionById(id);

        Assertions.assertNotNull(questionFromDb);
        Assertions.assertEquals(createdQuestion.getTitle(), questionFromDb.getTitle());
        Assertions.assertEquals(createdQuestion.getContent(), questionFromDb.getContent());
        Assertions.assertEquals(createdQuestion.getDifficulty(), questionFromDb.getDifficulty());
        Assertions.assertEquals(createdQuestion.getCategory(), questionFromDb.getCategory());
        assertThat(createdQuestion)
                .usingRecursiveComparison()
                .ignoringCollectionOrderInFields("tags", "additionalSources")
                .isEqualTo(questionFromDb);
    }

    @Test
    void testGetQuestionById_whenInvalidIdProvided_throwException(){
        UUID invalidId = UUID.randomUUID();
        Assertions.assertThrows(NoQuestionMatchingIdFoundException.class, () -> questionService.getQuestionById(invalidId));
    }

    @Test
    @DisplayName("Get Questions By Category")
    void testGetAllQuestionsByCategory_returnAllQuestions(){

        //Arrange

        Category category = Category.JAVA;

        List<Question> javaCategoryQuestions =
                new ArrayList<>(
                        Stream.generate(Question::new)
                                .map(this::populateObject)
                                .limit(10)
                                .toList()
                );

        questionRepository.saveAll(javaCategoryQuestions);

        List<Question> pythonCategoryQuestions =
                new ArrayList<>(
                        Stream.generate(Question::new)
                                .map(this::populateObject)
                                .limit(10)
                                .toList()
                );

        for (Question question : pythonCategoryQuestions){
            question.setCategory(Category.PYTHON);
        }

        questionRepository.saveAll(pythonCategoryQuestions);

        //Action
        List<Question> questions = questionService.getAllQuestionByCategory(category);

        //Assert
        Assertions.assertEquals(questions.size(), 10);
        Assertions.assertTrue(questions.stream().allMatch(e -> e.getCategory().equals(category)));
    }



    private Question populateObject(Question question){
        question.setCategory(Category.JAVA);
        question.setDifficulty(QuestionDifficulty.MEDIUM);
        question.setTitle("test");
        question.setContent("test");
        return question;
    }

}
