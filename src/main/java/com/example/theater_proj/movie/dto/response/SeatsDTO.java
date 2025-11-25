package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.SeatsBookingStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;

public record SeatsDTO(
        int seat_id,
        int row,
        int col,
        SeatsBookingStatus seatsBookingStatus
) {

}
