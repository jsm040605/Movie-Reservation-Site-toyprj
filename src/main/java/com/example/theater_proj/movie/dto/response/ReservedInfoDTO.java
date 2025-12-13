package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;

import java.time.LocalDateTime;

public record ReservedInfoDTO(
        Integer reservationId,
        Integer totalPrice,
        SeatsBookingStatus bookingStatus,
        PaymentStatus paymentStatus,
        LocalDateTime created_at
) {
}
