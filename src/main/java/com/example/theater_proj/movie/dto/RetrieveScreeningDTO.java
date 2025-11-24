package com.example.theater_proj.movie.dto;

import com.example.theater_proj.movie.RoomGrade;
import com.example.theater_proj.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RetrieveScreeningDTO {
    private int movieId;
    private RoomGrade roomGrade;
    private int price;

    private SeatsDTO[][] seatsDTOS;
}
