package org.example.techparanoiaserver.controller;

import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.response.QuestionDeleteResponse;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("api/questions")
public class QuestionController {
    
    private final QuestionService questionService;

    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping("/details")
    public ResponseEntity<Question> getQuestionById(@RequestParam("id") UUID id){
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }
    
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return ResponseEntity.ok(questionService.getAllQuestions());
    }

    @GetMapping("/by_category")
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@RequestParam("category") Category category){
        return ResponseEntity.ok(questionService.getAllQuestionByCategory(category));
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateRequest request){
        Question createdQuestion = questionService.createQuestion(request);
        return new ResponseEntity<>(createdQuestion, HttpStatus.CREATED);
    }

    @DeleteMapping
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@RequestParam("id") UUID id){
        Question deletedQuestion = questionService.deleteQuestion(id);

        QuestionDeleteResponse response = new QuestionDeleteResponse();
        response.setId(deletedQuestion.getId().toString());

        return ResponseEntity.ok(response);
    }

    @PatchMapping
    public ResponseEntity<Question> updateQuestion(@RequestParam("id") UUID id, @RequestBody QuestionCreateRequest request){
        return ResponseEntity.ok(questionService.updateQuestion(id, request));
    }

}
