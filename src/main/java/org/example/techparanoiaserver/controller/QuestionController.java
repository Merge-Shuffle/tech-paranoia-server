package org.example.techparanoiaserver.controller;

import org.example.techparanoiaserver.entity.Question.Category;
import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.request.QuestionCreateRequest;
import org.example.techparanoiaserver.response.QuestionDeleteResponse;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.example.techparanoiaserver.utils.ProdMethod;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping
    public ResponseEntity<Question> getQuestionById(@RequestParam("id") UUID id){
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

    @ProdMethod
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestions(){
        return null;
    }

    @ProdMethod
    @GetMapping
    public ResponseEntity<List<Question>> getAllQuestionsByCategory(@RequestParam("category") Category category){
        return null;
    }

    @ProdMethod
    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody QuestionCreateRequest request){
        return null;
    }

    @ProdMethod
    @DeleteMapping
    public ResponseEntity<QuestionDeleteResponse> deleteQuestion(@RequestParam("id") UUID id){
        Question deletedQuestion = questionService.deleteQuestion(id);

        QuestionDeleteResponse response = new QuestionDeleteResponse();
        response.setId(deletedQuestion.getId().toString());

        return ResponseEntity.ok(response);
    }

}
