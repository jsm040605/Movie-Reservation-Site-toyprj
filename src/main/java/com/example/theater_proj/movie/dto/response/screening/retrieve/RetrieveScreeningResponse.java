package com.example.theater_proj.movie.dto.response.screening.retrieve;

import com.example.theater_proj.movie.model.RoomGrade;

public record RetrieveScreeningResponse(
        Long screeningId,
        RoomGrade roomGrade,
        SeatsInfo[][] seatsInfos
) {

}
