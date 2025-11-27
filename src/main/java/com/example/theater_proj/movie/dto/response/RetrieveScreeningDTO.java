package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.RoomGrade;

public record RetrieveScreeningDTO(
        int screeningId,
        RoomGrade roomGrade,
        int price,
        SeatsDTO[][] seatsDTOS
) {

}
