package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.entity.Reservation;

public record ReservationResponse(
        int totalPrice,
        Reservation reservation
) {
}
