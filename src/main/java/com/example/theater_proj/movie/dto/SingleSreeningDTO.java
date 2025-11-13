package com.example.theater_proj.movie.dto;

import com.example.theater_proj.movie.RoomGrade;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class SingleSreeningDTO {
    private int id;
    private LocalDateTime startTime;
}
