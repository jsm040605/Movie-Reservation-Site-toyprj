package com.example.theater_proj.movie;

import com.example.theater_proj.movie.entity.Reservation;
import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.repository.JpaReservationRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class ReservationScheduler {
    private final JpaReservationRepository reservationRepository;

    @Scheduled(fixedDelay = 30000)
    @Transactional
    public void timeOverReservation(){
        LocalDateTime cutOffTime = LocalDateTime.now().minusSeconds(30);

        reservationRepository.deleteExpiredReservation(PaymentStatus.YET, cutOffTime);
    }
}
