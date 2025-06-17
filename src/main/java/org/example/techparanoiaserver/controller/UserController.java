package org.example.techparanoiaserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.request.ChangeEmailRequest;
import org.example.techparanoiaserver.request.ChangePasswordRequest;
import org.example.techparanoiaserver.service.auth.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
        @RequestBody @Valid ChangePasswordRequest request,
        Authentication connectedUser
    ){
        return ResponseEntity.ok(userService.changePassword(request, connectedUser));
    }

    @PostMapping
    public ResponseEntity<?> changeEmail(
            @RequestBody @Valid ChangeEmailRequest request
    ) {
        return null;
    }
}
