package org.example.techparanoiaserver.service.Question;

import org.example.techparanoiaserver.dto.AdditionalSourceDto;
import org.example.techparanoiaserver.entity.Question.AdditionalSourceType;
import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.entity.Question.QuestionDifficulty;
import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.example.techparanoiaserver.repository.Question.QuestionRepository;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class QuestionServiceTest {

    @InjectMocks
    private QuestionServiceImpl questionService;

    @Mock
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

        request.setCategory(Category.JAVA);

    }

    @Test
    void testCreateQuestion_returnSavedQuestion(){
        //Arrange
        Mockito.when(questionRepository.save(Mockito.any(Question.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Action
        Question captured = questionService.createQuestion(request);

        //Assert

        assertEquals(captured.getTitle(), request.getTitle());
        assertEquals(captured.getCategory(), request.getCategory());
        assertEquals(captured.getContent(), request.getContent());
        assertEquals(captured.getDifficulty(), request.getDifficulty());
        assertEquals(captured.getTags(), request.getTags());

        Assertions.assertNotNull(captured.getAdditionalSources());

        assertEquals(1, captured.getAdditionalSources().size());
        assertEquals("test.url", captured.getAdditionalSources().get(0).getUrl());
    }

    @Test
    void testUpdateQuestion_whenValidIdProvided_returnUpdatedQuestion(){
        //Arrange
        Question question = new Question();
        question.setId(UUID.randomUUID());

        Mockito.when(questionRepository.getQuestionById(Mockito.any(UUID.class)))
                .thenReturn(Optional.of(question));
        Mockito.when(questionRepository.save(Mockito.any(Question.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        //Act

        Question updatedQuestion =
                questionService.updateQuestion(question.getId(), request);

        //Assert

        assertEquals(question.getId(), updatedQuestion.getId());

        assertEquals(updatedQuestion.getTitle(), request.getTitle());
        assertEquals(updatedQuestion.getCategory(), request.getCategory());
        assertEquals(updatedQuestion.getContent(), request.getContent());
        assertEquals(updatedQuestion.getDifficulty(), request.getDifficulty());
        assertEquals(updatedQuestion.getTags(), request.getTags());

        Assertions.assertNotNull(updatedQuestion.getAdditionalSources());

        assertEquals(1, updatedQuestion.getAdditionalSources().size());
        assertEquals("test.url", updatedQuestion.getAdditionalSources().get(0).getUrl());
    }

    @Test
    void testUpdateQuestion_whenInvalidIdProvided_returnUpdatedQuestion(){
        //Arrange
        Mockito.when(questionRepository.getQuestionById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        //Assert & Act
        assertThrows(NoQuestionMatchingIdFoundException.class,
                () -> questionService.updateQuestion(UUID.randomUUID(), request));
    }


    @Test
    void testDeleteQuestion_returnDeletedQuestion(){
        //Arrange
        UUID id = UUID.randomUUID();
        Question question = new Question();
        question.setId(id);

        Mockito.when(questionRepository.getQuestionById(id))
                .thenReturn(Optional.of(question));

        //Act & Assert
        Question deletedQuestion =
                questionService.deleteQuestion(id);

        Assertions.assertNotNull(deletedQuestion);
        Assertions.assertEquals(deletedQuestion.getId(), question.getId());
    }

    @Test
    void testGetQuestionById_whenInvalidIdProvided_throwsException() {
        //Arrange
        UUID id = UUID.randomUUID();


        Mockito.when(questionRepository.getQuestionById(id))
                .thenReturn(Optional.empty());

        //Act & Assert

        Assertions.assertThrows(NoQuestionMatchingIdFoundException.class,
                () -> questionService.deleteQuestion(id));

    }



    @Test
    void testGetQuestionById_whenValidIdProvided_returnQuestion() {
        //Arrange
        UUID id = UUID.randomUUID();
        Question question = new Question();
        question.setId(id);

        Mockito.when(questionRepository.getQuestionById(id))
                .thenReturn(Optional.of(question));

        //Action

        Question returnedQuestion =
                questionService.getQuestionById(id);

        //Assert

        Assertions.assertNotNull(returnedQuestion);
        assertEquals(id, returnedQuestion.getId());
    }



    @Test
    void testGetQuestionById_whenInvalidIdProvided_throwException(){
        //Arrange
        Mockito.when(questionRepository.getQuestionById(Mockito.any(UUID.class)))
                .thenReturn(Optional.empty());

        //Assert & Act
        assertThrows(NoQuestionMatchingIdFoundException.class, () -> questionService.getQuestionById(UUID.randomUUID()));
    }


}
