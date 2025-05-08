package org.example.techparanoiaserver.config;

import org.example.techparanoiaserver.exception.NoQuestionMatchingIdFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoQuestionMatchingIdFoundException.class)
    public ResponseEntity<Map<String, String>> handleNoQuestionException(NoQuestionMatchingIdFoundException ex){
        Map<String, String> error = new HashMap<>();
        error.put("message", ex.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }
}
