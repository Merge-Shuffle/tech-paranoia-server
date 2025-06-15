package org.example.techparanoiaserver.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record RegisterRequest(
        @Email(message = "Invalid email format")
        @NotBlank(message = "Email can not be blank")
        @NotEmpty(message = "Email can not be empty")
        @NotNull(message = "Email can not be null")
        String email,
        @NotBlank(message = "Password can not be blank")
        @NotEmpty(message = "Password can not be empty")
        @NotNull(message = "Password can not be null")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String password,
        @NotBlank(message = "First name can not be blank")
        @NotEmpty(message = "First name can not be empty")
        @NotNull(message = "First name can not be null")
        String firstName,
        @NotBlank(message = "Last name can not be blank")
        @NotEmpty(message = "Last name can not be empty")
        @NotNull(message = "Last name can not be null")
        String lastName,
        @NotNull(message = "Date of birth can not be null")
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth
) { }
