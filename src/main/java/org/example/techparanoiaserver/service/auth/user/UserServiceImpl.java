package org.example.techparanoiaserver.service.auth.user;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.config.jwt.JwtService;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.entity.user.UserQuestion;
import org.example.techparanoiaserver.exception.EmailAlreadyInUseException;
import org.example.techparanoiaserver.exception.OperationNotPermittedException;
import org.example.techparanoiaserver.repository.UserQuestionRepository;
import org.example.techparanoiaserver.repository.user.UserRepository;
import org.example.techparanoiaserver.request.ChangeDetailsRequest;
import org.example.techparanoiaserver.request.ChangeEmailRequest;
import org.example.techparanoiaserver.request.ChangePasswordRequest;
import org.example.techparanoiaserver.response.ChangeDetailsResponse;
import org.example.techparanoiaserver.response.ChangeEmailResponse;
import org.example.techparanoiaserver.response.ChangePasswordResponse;
import org.example.techparanoiaserver.response.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;
    private final UserQuestionRepository userQuestionRepository;

    @Override
    public ChangePasswordResponse changePassword(ChangePasswordRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        if (!passwordEncoder.matches(request.oldPassword(), user.getPassword())){
            throw new BadCredentialsException("Old password is incorrect");
        }

        if (!request.oldPassword().equals(request.oldPasswordConf())){
            throw new BadCredentialsException("Old password confirmation is invalid");
        }

        if (request.oldPassword().equals(request.newPassword())){
            throw new BadCredentialsException("New password must be different from the old one");
        }

        if (!request.newPassword().equals(request.newPasswordConf())){
            throw new BadCredentialsException("New password and new password confirmation does not match");
        }

        user.setPassword(passwordEncoder.encode(request.newPassword()));

        return ChangePasswordResponse.builder()
                .userId(repository.save(user).getId())
                .message("Password changed successfully")
                .build();
    }

    @Override
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();

        if (repository.findByEmail(request.newEmail()).isPresent()){
            throw new EmailAlreadyInUseException("Email is already in use");
        }

        if (!user.getEmail().equals(request.oldEmail())){
            throw new OperationNotPermittedException("You can not change email of the account you don't own.");
        }

        if (request.oldEmail().equals(request.newEmail())){
            throw new BadCredentialsException("New email must be different from the old one");
        }

        if (!request.newEmail().equals(request.newEmailConf())){
            throw new BadCredentialsException("New email confirmation is invalid");
        }

        user.setEmail(request.newEmail());
        repository.save(user);

        String token = jwtService.generateToken(new HashMap<>(), user);

        return ChangeEmailResponse.builder()
                .jwt(token)
                .build();
    }

    @Override
    public UserResponse getUserDetails(Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        Pageable pageable = PageRequest.of(0, 10, Sort.by("addedAt").descending());
        Page<UserQuestion> userQuestions = userQuestionRepository.findAllByUserId(user.getId(), pageable);
        return userMapper.toResponse(user, userQuestions);
    }

    @Override
    public ChangeDetailsResponse changeUserDetails(ChangeDetailsRequest request, Authentication connectedUser) {
        User user = (User) connectedUser.getPrincipal();
        user.setFirstName(request.firstName());
        user.setLastName(request.lastName());
        user.setDateOfBirth(request.dateOfBirth());
        User savedUser = repository.save(user);
        return ChangeDetailsResponse.builder()
                .firstName(savedUser.getFirstName())
                .lastName(savedUser.getLastName())
                .email(savedUser.getEmail())
                .userId(savedUser.getId())
                .dateOfBirth(savedUser.getDateOfBirth())
                .build();
    }

}
