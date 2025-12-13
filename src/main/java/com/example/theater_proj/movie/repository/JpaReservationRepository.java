package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Reservation;
import com.example.theater_proj.movie.model.PaymentStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface JpaReservationRepository extends JpaRepository<Reservation, Integer> {
    List<Reservation> getReservationById(int id);

    @Modifying
    @Query(
            "delete from Reservation r " +
                    "where r.paymentStauts = :status and r.created_at < :cut_off_time"
    )
    void deleteExpiredReservation(
            @Param("status") PaymentStatus status, @Param("cut_off_time") LocalDateTime cut_off_time);
}
