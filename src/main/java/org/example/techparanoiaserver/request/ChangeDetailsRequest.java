package org.example.techparanoiaserver.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ChangeDetailsRequest(
        @NotNull
        @NotBlank
        @NotEmpty
        String firstName,
        @NotNull
        @NotBlank
        @NotEmpty
        String lastName,
        @NotNull
        @JsonFormat(pattern = "yyyy-MM-dd")
        LocalDate dateOfBirth
) {
}
