package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.SeatsBookingStatus;

public record ReservedInfoDTO(
        Integer reservationId,
        Integer totalPrice,
        SeatsBookingStatus bookingStatus
) {
}
