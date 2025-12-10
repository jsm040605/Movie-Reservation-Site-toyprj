package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.RoomGrade;

public record ReservedRoomDTO(
        Integer roomNumber,
        RoomGrade roomGrade
) {
}
