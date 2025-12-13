package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.exception.MovieNotFoundException;
import com.example.theater_proj.movie.model.PaymentStatus;
import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.*;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.exception.AlreadyReservedException;
import com.example.theater_proj.movie.repository.*;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class ReservationService {
    private final JpaReservationRepository reservationRepository;
    private final JpaScreeningRepository screeningRepository;
    private final JpaSeatRepository seatRepository;

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
        List<Seats> seats = seatRepository.findAllById(seatsIds);

        Map<Integer, SeatsBookingStatus> reservedSeats = findReservationSeatsBy(screening);

        for (Seats seat : seats) {
            SeatsBookingStatus status = reservedSeats.getOrDefault(seat.getId(), SeatsBookingStatus.AVAILABLE);

            if (!status.equals(SeatsBookingStatus.AVAILABLE)) {
                throw new AlreadyReservedException("id: "+ seat.getId()+ " is already reserved");
            }
        }

        Reservation reservation = new Reservation();
        reservation.setScreening(screening);
        reservation.setBookingStatus(SeatsBookingStatus.LOCKED);
        reservation.setPaymentStauts(PaymentStatus.YET);
        reservation.setCreated_at(LocalDateTime.now());

        int totalPrice = price * seats.size();
        reservation.setTotalPrice(totalPrice);

        for (Seats seat : seats) {
            ReservationDetail reservationDetail = new ReservationDetail(seat);

            reservation.addReservationDetail(reservationDetail);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        return convertToReservationReseponseDTO(screening, seats, savedReservation);
    }

    public ReservationResponse findReservationById(int id) {
        Optional<Reservation> reserv = reservationRepository.findById(id);

        if (!reserv.isPresent()) {
            throw new IllegalArgumentException("id- " + id);
        }

        Reservation reservation = reserv.get();


        List<Seats> seats = new ArrayList<>();

        List<ReservationDetail> reservationDetails = reservation.getReservationDetails();
        for (ReservationDetail reservationDetail : reservationDetails) {
            seats.add(reservationDetail.getSeat());
        }

        return convertToReservationReseponseDTO(
                reservation.getScreening(),
                seats,
                reservation
        );
    }

    public Map<Integer, SeatsBookingStatus> findReservationSeatsBy(Screening screening){
        List<Reservation> reservations = screening.getReservations();

        Map<Integer, SeatsBookingStatus> reservedSeats = new HashMap<>();

        for (Reservation reservation : reservations) {
            List<ReservationDetail> reservationDetails = reservation.getReservationDetails();
            for (ReservationDetail reservationDetail : reservationDetails) {
                Seats seat = reservationDetail.getSeat();
                reservedSeats.put(seat.getId(), reservation.getBookingStatus());
            }
        }

        return reservedSeats;
    }

    private ReservationResponse convertToReservationReseponseDTO(Screening screening, List<Seats> seats, Reservation reservation) {
        Movie movie = screening.getMovie();
        ReservedMovieDTO movieDTO = new ReservedMovieDTO(movie.getId(), movie.getTitle(), movie.getRunningTime());
        ReservedScreeningDTO screeningDTO = new ReservedScreeningDTO(screening.getStartTime(), screening.getEndTime());

        Room room = screening.getRoom();
        ReservedRoomDTO roomDTO = new ReservedRoomDTO(room.getRoomNumber(), room.getRoomGrade());

        List<ReservedSeatsDTO> reservedSeatsDTOS = new ArrayList<>();
        for (Seats seat : seats) {
            ReservedSeatsDTO seatsDTO = new ReservedSeatsDTO(seat.getRow(), seat.getCol());
            reservedSeatsDTOS.add(seatsDTO);
        }

        ReservedInfoDTO reservedInfoDTO = new ReservedInfoDTO(
                reservation.getId(),
                reservation.getTotalPrice(),
                reservation.getBookingStatus(),
                reservation.getPaymentStauts(),
                reservation.getCreated_at()
        );

        return new ReservationResponse(
                movieDTO,
                screeningDTO,
                roomDTO,
                reservedSeatsDTOS,
                reservedInfoDTO
        );
    }
}
