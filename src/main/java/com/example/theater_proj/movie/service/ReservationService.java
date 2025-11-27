package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.ReservationResponse;
import com.example.theater_proj.movie.entity.Reservation;
import com.example.theater_proj.movie.entity.Screening;
import com.example.theater_proj.movie.entity.Seat;
import com.example.theater_proj.movie.repository.JpaSeatRepository;
import com.example.theater_proj.movie.repository.ReservationRepository;
import com.example.theater_proj.movie.repository.ScreeningRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {
    ReservationRepository reservationRepository;
    ScreeningRepository screeningRepository;
    JpaSeatRepository seatRepository;

    public ReservationService(
            ReservationRepository reservationRepository,
            ScreeningRepository screeningRepository,
            JpaSeatRepository seatRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
    }

    public ReservationResponse makeReservations(int screeningId, List<Integer> seatsIds, int price){
        Screening screening = screeningRepository.findScreeningById(screeningId).get();

        List<Seat> seats = seatRepository.findAllById(seatsIds);

        Reservation reservation = new Reservation();

        reservation.setScreening(screening);
        reservation.setSeats(seats);
        reservation.setBookingStatus(SeatsBookingStatus.LOCKED);


        Reservation savedReserve = reservationRepository.save(reservation);
        int totalPrice = reservation.getSeats().size() * price;

        return new ReservationResponse(totalPrice, savedReserve);
    }
}
