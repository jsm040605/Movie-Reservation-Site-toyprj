package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;

public record ReservedInfoDTO(
        Integer reservationId,
        Integer totalPrice,
        SeatsBookingStatus bookingStatus,
        PaymentStatus paymentStatus
) {
}
