package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ChangePasswordRequest(
        @NotNull(message = "Old password can not be null")
        @NotBlank(message = "Old password can not be blank")
        @NotEmpty(message = "Old password can not be empty")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String oldPassword,
        @NotNull(message = "Old password confirmation can not be null")
        @NotBlank(message = "Old password confirmation can not be blank")
        @NotEmpty(message = "Old password confirmation can not be empty")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String oldPasswordConf,
        @NotNull(message = "New password can not be null")
        @NotBlank(message = "New password can not be blank")
        @NotEmpty(message = "New password can not be empty")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String newPassword,
        @NotNull(message = "New password confirmation can not be null")
        @NotBlank(message = "New password confirmation can not be blank")
        @NotEmpty(message = "New password confirmation can not be empty")
        @Size(min = 8, message = "Password can not have less than 8 characters")
        String newPasswordConf
) { }
