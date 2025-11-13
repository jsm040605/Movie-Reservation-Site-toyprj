package com.example.theater_proj.movie.dto;

import com.example.theater_proj.movie.RoomGrade;
import lombok.Getter;

import java.util.List;

@Getter
public class RoomScreeningDTO {
    private int roomNumber;
    private RoomGrade roomGrade;
    private List<SingleSreeningDTO> screenings;

    public RoomScreeningDTO(int roomNumber, RoomGrade roomGrade, List<SingleSreeningDTO> screenings) {
        this.roomNumber = roomNumber;
        this.roomGrade = roomGrade;
        this.screenings = screenings;
    }
}
