package org.example.techparanoiaserver.exception;

import java.util.UUID;

public class NoQuestionMatchingIdFoundException extends RuntimeException {
    public NoQuestionMatchingIdFoundException(UUID id) {
        super("No question matching " + id + " found");
    }
}
