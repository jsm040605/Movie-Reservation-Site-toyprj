package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.RoomGrade;

public record ReservedRoomDTO(
        Integer roomNumber,
        RoomGrade roomGrade
) {
}
