package com.example.theater_proj.movie.dto;

import com.example.theater_proj.movie.SeatsBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SeatsDTO {
    int seat_id;
    int row;
    int col;
    SeatsBookingStatus seatsBookingStatus;
}
