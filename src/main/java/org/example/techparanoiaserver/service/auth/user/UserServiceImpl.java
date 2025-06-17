package org.example.techparanoiaserver.service.auth.user;

import lombok.RequiredArgsConstructor;
import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.repository.user.UserRepository;
import org.example.techparanoiaserver.request.ChangeEmailRequest;
import org.example.techparanoiaserver.request.ChangePasswordRequest;
import org.example.techparanoiaserver.response.ChangeEmailResponse;
import org.example.techparanoiaserver.response.ChangePasswordResponse;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{
    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;

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
    public ChangeEmailResponse changeEmail(ChangeEmailRequest request) {
        return null;
    }
}
