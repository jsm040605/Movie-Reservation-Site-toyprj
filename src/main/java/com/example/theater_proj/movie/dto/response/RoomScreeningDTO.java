package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.RoomGrade;

import java.util.List;

public record RoomScreeningDTO(
        int roomNumber,
        RoomGrade roomGrade,
        List<SingleSreeningDTO> screenings
) {

}
