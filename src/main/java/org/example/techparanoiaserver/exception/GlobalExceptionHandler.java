package org.example.techparanoiaserver.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(NoQuestionMatchingIdFoundException.class)
    public ResponseEntity<String> handle(NoQuestionMatchingIdFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handle(MethodArgumentNotValidException exception){
        var errors = new HashMap<String, String>();

        exception.getBindingResult().getAllErrors()
                .forEach(error -> {
                    var field = ((FieldError) error).getField();
                    var errorMessage = error.getDefaultMessage();
                    errors.put(field, errorMessage);
                });

        return ResponseEntity.badRequest()
                .body(new ErrorResponse(errors));
    }

    @ExceptionHandler(QuestionTitleInUseException.class)
    public ResponseEntity<String> handle(QuestionTitleInUseException exception){
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<String> handle(BadCredentialsException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(exception.getMessage());
    }

    @ExceptionHandler(EmailAlreadyInUseException.class)
    public ResponseEntity<String> handle(EmailAlreadyInUseException exception){
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }

    @ExceptionHandler(OperationNotPermittedException.class)
    public ResponseEntity<String> handle(OperationNotPermittedException exception){
        return ResponseEntity.badRequest()
                .body(exception.getMessage());
    }
}
