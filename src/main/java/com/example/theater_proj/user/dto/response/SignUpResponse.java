package com.example.theater_proj.user.dto.response;

import com.example.theater_proj.user.model.UserGrade;

public record SignUpResponse(
    long id,
    String email,
    String name,
    UserGrade userGrade
) {
}
