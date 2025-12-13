package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(
        name = "Reservation",
        indexes = @Index(name = "index_PaymentStatus_CreatedAt", columnList = "payment_status, created_at")
)
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private SeatsBookingStatus bookingStatus;

    @Column(name = "total_amount")
    private int totalPrice;

    @Column(name = "payment_status")
    private PaymentStatus paymentStauts;

    private LocalDateTime created_at;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToMany(mappedBy = "reservation", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<ReservationDetail> reservationDetails = new ArrayList<>();

    public void addReservationDetail(ReservationDetail reservationDetail) {
        this.reservationDetails.add(reservationDetail);
        reservationDetail.setReservation(this);
    }
}
