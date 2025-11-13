package com.example.theater_proj.user.controller;

import com.example.theater_proj.user.dto.request.LoginRequest;
import com.example.theater_proj.user.dto.request.SignUpRequest;
import com.example.theater_proj.user.dto.response.SignUpResponse;
import com.example.theater_proj.user.dto.response.UserResponse;
import com.example.theater_proj.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Tag(name = "user-controller", description = "사용자 회원가입, 로그인 등을 위한 컨트롤러입니다.")
public class UserController {

    private final UserService userService;

    @Operation(summary = "사용자 회원가입 폼", description = "사용자의 회원가입을 위한 화면을 제공합니다.")
    @GetMapping(path = "/signup")
    public String showSignUpForm() {
        return "signup";
    }

    @Operation(summary = "사용자 회원가입 요청", description = "사용자 회원가입을 진행합니다.")
    @PostMapping(path = "/signup")
    public ResponseEntity<SignUpResponse> signup(
            @RequestBody SignUpRequest signUpRequest) {

        SignUpResponse signUpResponse = userService.signUp(signUpRequest);
        // 나중에 redirect:home 또는 login으로 바꿀 것
        return ResponseEntity.ok(signUpResponse);
    }

    @GetMapping(path = "/{id}") // todo : view 추가 -> redirect
    public ResponseEntity<UserResponse> retrieveUserById(@PathVariable Long id) throws Exception {

        UserResponse userResponse = userService.retrieveById(id);

        return ResponseEntity.ok(userResponse);
    }

    @PostMapping(path = "/login")
    public ResponseEntity<UserResponse> login(HttpSession session, @RequestBody LoginRequest loginRequest) throws Exception {

        UserResponse loginUser= userService.login(loginRequest);

        session.setAttribute("user", loginUser);

        return ResponseEntity.ok(loginUser);
    }
}
