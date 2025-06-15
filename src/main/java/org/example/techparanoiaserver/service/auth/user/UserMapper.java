package org.example.techparanoiaserver.service.auth.user;

import org.example.techparanoiaserver.entity.user.User;
import org.example.techparanoiaserver.response.RegisterResponse;
import org.springframework.stereotype.Service;

@Service
public class UserMapper {
    public RegisterResponse toRegisterResponse(User user){
        return RegisterResponse.builder()
                .id(user.getId())
                .email(user.getEmail())
                .dateOfBirth(user.getDateOfBirth())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .roles(user.getRoles())
                .build();
    }
}
