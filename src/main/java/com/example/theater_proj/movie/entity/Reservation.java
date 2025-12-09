package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Reservation {
    @Id
    @GeneratedValue
    @Column(name = "reservation_id")
    private int id;

    @Enumerated(EnumType.STRING)
    private SeatsBookingStatus bookingStatus;

    @Column(name = "total_amount")
    private int totalPrice;

    @Column(name = "payment_status")
    private PaymentStatus paymentStauts;

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
