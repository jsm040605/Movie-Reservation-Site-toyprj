package com.example.theater_proj.user.service;

import com.example.theater_proj.user.dto.request.LoginRequest;
import com.example.theater_proj.user.dto.request.SignUpRequest;
import com.example.theater_proj.user.dto.response.SignUpResponse;
import com.example.theater_proj.user.dto.response.UserResponse;
import com.example.theater_proj.user.entity.User;
import com.example.theater_proj.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public SignUpResponse signUp(SignUpRequest signUpRequest) {

        User user = new User(
                signUpRequest.email(),
                signUpRequest.password(),
                signUpRequest.name(),
                signUpRequest.phoneNumber()
        );

        userRepository.save(user);

        return new SignUpResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getUserGrade()
        );
    }

    public UserResponse retrieveById(long id) throws Exception {

        Optional<User> user = userRepository.findById(id);

        if (user.isEmpty()) {
            throw new Exception("id : " + id);
        }

        User retrievedUser = user.get();

        return new UserResponse(
                retrievedUser.getId(),
                retrievedUser.getEmail(),
                retrievedUser.getName(),
                retrievedUser.getPhoneNumber(),
                retrievedUser.getUserGrade()
        );
    }

    public UserResponse login(LoginRequest loginRequest) throws Exception {

        Optional<User> user = userRepository.findById(loginRequest.id());

        if(user.isEmpty()) {
            throw new Exception("id : " + loginRequest.id());
        }
        User retrievedUser = user.get();
        if(!retrievedUser.getPassword().equals(loginRequest.password())) {
            throw new Exception("password not match");
        }

        return new UserResponse(
                retrievedUser.getId(),
                retrievedUser.getEmail(),
                retrievedUser.getName(),
                retrievedUser.getPhoneNumber(),
                retrievedUser.getUserGrade()
        );
    }

}
