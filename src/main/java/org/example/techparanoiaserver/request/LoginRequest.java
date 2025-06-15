package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.*;

public record LoginRequest(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email can not be blank")
        @NotEmpty(message = "Email can not be empty")
        @NotNull(message = "Email can not be null")
        String email,
        @NotBlank(message = "Password can not be blank")
        @NotEmpty(message = "Password can not be empty")
        @NotNull(message = "Password can not be null")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String password
) {
}
