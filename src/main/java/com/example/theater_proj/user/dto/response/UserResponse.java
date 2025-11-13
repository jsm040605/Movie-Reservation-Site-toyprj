package com.example.theater_proj.user.dto.response;

import com.example.theater_proj.user.model.UserGrade;

public record UserResponse(
        long id,
        String email,
        String name,
        String phoneNumber,
        UserGrade userGrade
){
}
