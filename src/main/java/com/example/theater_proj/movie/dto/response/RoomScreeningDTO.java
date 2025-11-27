package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.RoomGrade;
import lombok.Getter;

import java.util.List;

public record RoomScreeningDTO(
        int roomNumber,
        RoomGrade roomGrade,
        List<SingleSreeningDTO> screenings
) {

}
