package org.example.techparanoiaserver.response;

import lombok.Data;

@Data
public class QuestionDeleteResponse {
    private String id;
    private String message = "Question successfully deleted";
}
