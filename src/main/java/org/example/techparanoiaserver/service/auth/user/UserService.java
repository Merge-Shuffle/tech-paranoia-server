package org.example.techparanoiaserver.service.auth.user;

import org.example.techparanoiaserver.request.ChangeEmailRequest;
import org.example.techparanoiaserver.request.ChangePasswordRequest;
import org.example.techparanoiaserver.response.ChangeEmailResponse;
import org.example.techparanoiaserver.response.ChangePasswordResponse;
import org.example.techparanoiaserver.response.UserResponse;
import org.springframework.security.core.Authentication;

public interface UserService {
    ChangePasswordResponse changePassword(ChangePasswordRequest request, Authentication connectedUser);
    ChangeEmailResponse changeEmail(ChangeEmailRequest request, Authentication connectedUser);
    UserResponse getUserDetails(Authentication connectedUser);
}
