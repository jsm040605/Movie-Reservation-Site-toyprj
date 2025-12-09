package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.SeatsBookingStatus;

public record SeatsDTO(
        int seat_id,
        int row,
        int col,
        SeatsBookingStatus seatsBookingStatus
) {

}
