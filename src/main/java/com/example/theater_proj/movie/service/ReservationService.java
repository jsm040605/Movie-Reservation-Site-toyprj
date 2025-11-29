package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.*;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReservationService {
    JpaReservationRepository reservationRepository;
    JpaScreeningRepository screeningRepository;
    JpaSeatRepository seatRepository;

    public ReservationService(
            JpaReservationRepository reservationRepository,
            JpaScreeningRepository screeningRepository,
            JpaSeatRepository seatRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.screeningRepository = screeningRepository;
        this.seatRepository = seatRepository;
    }

    public ReservationResponse makeReservations(int screeningId, List<Integer> seatsIds, int price){
        Screening screening = screeningRepository.findById(screeningId).get();
        List<Seat> seats = seatRepository.findAllById(seatsIds);

        Reservation reservation = new Reservation();
        reservation.setScreening(screening);
        reservation.setBookingStatus(SeatsBookingStatus.LOCKED);

        int totalPrice = price * seats.size();
        reservation.setTotalPrice(totalPrice);

        for (Seat seat : seats) {
            ReservationDetail reservationDetail = new ReservationDetail(seat);

            reservation.addReservationDetail(reservationDetail);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        return getReservationResponse(screening, seats, savedReservation);
    }

    private ReservationResponse getReservationResponse(Screening screening, List<Seat> seats, Reservation reservation) {
        Movie movie = screening.getMovie();
        ReservedMovieDTO movieDTO = new ReservedMovieDTO(movie.getId(), movie.getTitle(), movie.getRunningTime());
        ReservedScreeningDTO screeningDTO = new ReservedScreeningDTO(screening.getStartTime(), screening.getEndTime());

        Room room = screening.getRoom();
        ReservedRoomDTO roomDTO = new ReservedRoomDTO(room.getRoomNumber(), room.getRoomGrade());

        List<ReservedSeatsDTO> reservedSeatsDTOS = new ArrayList<>();
        for (Seat seat : seats) {
            ReservedSeatsDTO seatsDTO = new ReservedSeatsDTO(seat.getRow(), seat.getCol());
            reservedSeatsDTOS.add(seatsDTO);
        }

        ReservedInfoDTO reservedInfoDTO = new ReservedInfoDTO(
                reservation.getId(),
                reservation.getTotalPrice(),
                reservation.getBookingStatus());

        return new ReservationResponse(
                movieDTO,
                screeningDTO,
                roomDTO,
                reservedSeatsDTOS,
                reservedInfoDTO
        );
    }
}
