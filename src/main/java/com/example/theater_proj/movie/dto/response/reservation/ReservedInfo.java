package com.example.theater_proj.movie.dto.response.reservation;

import com.example.theater_proj.movie.dto.response.theater.TheaterListResponse;
import com.example.theater_proj.movie.entity.Reservation;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;

import java.time.LocalDateTime;

public record ReservedInfo(
        Long reservationId,
        Integer totalPrice,
        SeatsBookingStatus bookingStatus,
        PaymentStatus paymentStatus,
        LocalDateTime created_at
) {
    public static ReservedInfo fromEntity(Reservation reservation){
        return new ReservedInfo(
                reservation.getId(),
                reservation.getTotalPrice(),
                reservation.getBookingStatus(),
                reservation.getPaymentStauts(),
                reservation.getCreated_at()
        );
    }
}
