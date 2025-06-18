package org.example.techparanoiaserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.request.ChangeDetailsRequest;
import org.example.techparanoiaserver.request.ChangeEmailRequest;
import org.example.techparanoiaserver.request.ChangePasswordRequest;
import org.example.techparanoiaserver.response.ChangeDetailsResponse;
import org.example.techparanoiaserver.service.auth.user.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping
    public ResponseEntity<?> getUserDetails(Authentication connectedUser){
        return ResponseEntity.ok(userService.getUserDetails(connectedUser));
    }

    @PostMapping("/change-password")
    public ResponseEntity<?> changePassword(
        @RequestBody @Valid ChangePasswordRequest request,
        Authentication connectedUser
    ){
        return ResponseEntity.ok(userService.changePassword(request, connectedUser));
    }

    @PostMapping("/change-email")
    public ResponseEntity<?> changeEmail(
            @RequestBody @Valid ChangeEmailRequest request,
            Authentication connectedUser
    ) {
        return ResponseEntity.ok(userService.changeEmail(request, connectedUser));
    }

    @PatchMapping("/change-details")
    public ResponseEntity<ChangeDetailsResponse> changeUserDetails(
            @RequestBody @Valid ChangeDetailsRequest request,
            Authentication connectedUser
    ){
        return ResponseEntity.ok(userService.changeUserDetails(request, connectedUser));
    }
}
