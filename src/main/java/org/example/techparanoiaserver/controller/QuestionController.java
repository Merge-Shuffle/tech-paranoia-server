package org.example.techparanoiaserver.controller;

import org.example.techparanoiaserver.entity.Question.Question;
import org.example.techparanoiaserver.service.Question.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("api/questions")
public class QuestionController {


    @Autowired
    public QuestionController(QuestionService questionService){
        this.questionService = questionService;
    }

    @GetMapping
    public ResponseEntity<Question> getQuestionById(@RequestParam("id") UUID id){
        return ResponseEntity.ok(questionService.getQuestionById(id));
    }

}
