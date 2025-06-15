package org.example.techparanoiaserver.service.auth;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.repository.user.RoleRepository;
import org.example.techparanoiaserver.repository.user.UserRepository;
import org.example.techparanoiaserver.request.LoginRequest;
import org.example.techparanoiaserver.request.RegisterRequest;
import org.example.techparanoiaserver.response.LoginResponse;
import org.example.techparanoiaserver.response.RegisterResponse;
import org.example.techparanoiaserver.service.auth.user.UserMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterResponse registerUser(RegisterRequest request){
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER was not found"));

        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .dateOfBirth(request.dateOfBirth())
                .accountLocked(false)
                .enabled(false)
                .roles(List.of(userRole))
                .build();

        var savedUser = repository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

    public LoginResponse authenticateUser(LoginRequest request) {

        return null;
    }
}
