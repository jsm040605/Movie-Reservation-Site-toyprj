package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.RoomGrade;

public record RetrieveScreeningDTO(
        int screeningId,
        RoomGrade roomGrade,
        SeatsDTO[][] seatsDTOS
) {

}
