package org.example.techparanoiaserver.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.request.LoginRequest;
import org.example.techparanoiaserver.request.RegisterRequest;
import org.example.techparanoiaserver.response.LoginResponse;
import org.example.techparanoiaserver.response.RegisterResponse;
import org.example.techparanoiaserver.service.auth.AuthService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService service;

    @PostMapping("/register")
    public ResponseEntity<RegisterResponse> register(
            @RequestBody @Valid RegisterRequest request
    ) {
        return new ResponseEntity<>(service.registerUser(request), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(
            @RequestBody @Valid LoginRequest request
    ) {
      return ResponseEntity.ok(service.authenticateUser(request));
    }
}
