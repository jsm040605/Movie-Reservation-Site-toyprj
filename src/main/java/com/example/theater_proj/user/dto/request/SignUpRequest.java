package com.example.theater_proj.user.dto.request;

public record SignUpRequest(
        String email,
        String password,
        String name,
        String phoneNumber
) {
}
