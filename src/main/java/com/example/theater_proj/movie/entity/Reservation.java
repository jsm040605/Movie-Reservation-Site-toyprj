package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.coyote.BadRequestException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reservation_id")
    private Long id;

    @Enumerated(EnumType.STRING)
    private SeatsBookingStatus bookingStatus;

    @Column(name = "payment_status")
    private PaymentStatus paymentStauts;

    private LocalDateTime created_at;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ReservationDetail> reservationDetails = new ArrayList<>();

    //연관관계 매핑 메서드
    public void addReservationDetail(ReservationDetail reservationDetail) {
        this.reservationDetails.add(reservationDetail);
        reservationDetail.setReservation(this);
    }

    //static factory method
    public static Reservation makeReservation(Screening screening, List<ReservationDetail> reservationDetails) throws BadRequestException {
        Reservation reservation = new Reservation();

        for (ReservationDetail reservationDetail : reservationDetails) {
            if(screening.checkReservedSeats(reservationDetail.getSeat())){
                throw new BadRequestException();
            }
        }

        for (ReservationDetail reservationDetail : reservationDetails) {
            reservation.addReservationDetail(reservationDetail);
            screening.addReservationDetail(reservationDetail);
        }

        screening.reduceSeatsCount(reservationDetails.size());
        reservation.setBookingStatus(SeatsBookingStatus.LOCKED);
        reservation.setCreated_at(LocalDateTime.now());
        return reservation;
    }

    //business method (미구현)
    public void cancelReservation() {

    }

    //get method
    public int getTotalPrice() {
        int totalPrice = 0;
        for (ReservationDetail reservationDetail : reservationDetails) {
            totalPrice += reservationDetail.getReservationPrice();
        }
        return totalPrice;
    }

    public Screening getScreening() {
        ReservationDetail reservationDetail = reservationDetails.stream()
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        return reservationDetail.getScreening();
    }

    //특정 예약에서 예약된 좌석 조회
    public List<Seats> getReservedSeats() {
        return reservationDetails.stream().map(ReservationDetail::getSeat).toList();
    }
}
