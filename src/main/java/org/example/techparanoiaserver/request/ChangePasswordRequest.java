package org.example.techparanoiaserver.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public record ChangePasswordRequest(
        @NotNull(message = "Old password can not be null")
        @NotBlank(message = "Old password can not be blank")
        @NotEmpty(message = "Old password can not be empty")
        String oldPassword,
        @NotNull(message = "Old password confirmation can not be null")
        @NotBlank(message = "Old password confirmation can not be blank")
        @NotEmpty(message = "Old password confirmation can not be empty")
        String oldPasswordConf,
        @NotNull(message = "New password can not be null")
        @NotBlank(message = "New password can not be blank")
        @NotEmpty(message = "New password can not be empty")
        String newPassword,
        @NotNull(message = "New password confirmation can not be null")
        @NotBlank(message = "New password confirmation can not be blank")
        @NotEmpty(message = "New password confirmation can not be empty")
        String newPasswordConf
) { }
