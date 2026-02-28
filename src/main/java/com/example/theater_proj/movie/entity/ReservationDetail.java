package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.exception.AlreadyReservedException;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class ReservationDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_detail_id")
    private Long id;

    @Column(name = "reservation_price")
    private int reservationPrice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "seats_id")
    private Seats seat;

    public static ReservationDetail makeReservationDeatil(Screening screening, Seats seat) {
        ReservationDetail reservationDetail = new ReservationDetail();
        reservationDetail.setScreening(screening);
        reservationDetail.setSeat(seat);
        reservationDetail.setReservationPrice(screening.getPrice());
        return reservationDetail;
    }
}
