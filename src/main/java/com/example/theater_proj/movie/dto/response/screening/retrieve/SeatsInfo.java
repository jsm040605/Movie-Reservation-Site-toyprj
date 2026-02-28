package com.example.theater_proj.movie.dto.response.screening.retrieve;

import com.example.theater_proj.movie.model.SeatsBookingStatus;

public record SeatsInfo(
        Long seat_id,
        int row,
        int col,
        SeatsBookingStatus seatsBookingStatus
) {

}
