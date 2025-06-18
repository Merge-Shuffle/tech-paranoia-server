package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangeEmailRequest(
        @NotNull(message = "Old email can not be null")
        @NotBlank(message = "Old email can not be blank")
        @NotEmpty(message = "Old email can not be empty")
        @Email(message = "Email format is invalid")
        String oldEmail,
        @NotNull(message = "New email can not be null")
        @NotBlank(message = "New email can not be blank")
        @NotEmpty(message = "New email can not be empty")
        @Email(message = "Email format is invalid")
        String newEmail,
        @NotNull(message = "New email confirmation can not be null")
        @NotBlank(message = "New email confirmation can not be blank")
        @NotEmpty(message = "New email confirmation can not be empty")
        @Email(message = "Email format is invalid")
        String newEmailConf
) {
}
