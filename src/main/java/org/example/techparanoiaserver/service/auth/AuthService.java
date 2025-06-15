package org.example.techparanoiaserver.service.auth;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.config.jwt.JwtService;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.repository.user.RoleRepository;
import org.example.techparanoiaserver.repository.user.UserRepository;
import org.example.techparanoiaserver.request.LoginRequest;
import org.example.techparanoiaserver.request.RegisterRequest;
import org.example.techparanoiaserver.response.LoginResponse;
import org.example.techparanoiaserver.response.RegisterResponse;
import org.example.techparanoiaserver.service.auth.user.UserMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserMapper userMapper;
    private final UserRepository repository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public RegisterResponse registerUser(RegisterRequest request){
        var userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new IllegalStateException("Role USER was not found"));

        var user = User.builder()
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .firstName(request.firstName())
                .lastName(request.lastName())
                .dateOfBirth(request.dateOfBirth())
                .roles(List.of(userRole))
                .accountLocked(false)
                .enabled(false)
                .build();

        var savedUser = repository.save(user);

        return userMapper.toRegisterResponse(savedUser);
    }

    public LoginResponse authenticateUser(LoginRequest request) {

        var auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.email(), request.password())
        );

        var claims = new HashMap<String, Object>();
        var user = (User) auth.getPrincipal();
        var jwt = jwtService.generateToken(claims, user);
        return LoginResponse.builder()
                .jwt(jwt)
                .build();
    }
}
