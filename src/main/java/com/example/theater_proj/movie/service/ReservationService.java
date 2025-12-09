package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.model.SeatsBookingStatus;
import com.example.theater_proj.movie.dto.response.*;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.exception.AlreadyReservedException;
import com.example.theater_proj.movie.repository.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        int totalPrice = price * seats.size();
        reservation.setTotalPrice(totalPrice);

        for (Seats seat : seats) {
            ReservationDetail reservationDetail = new ReservationDetail(seat);

            reservation.addReservationDetail(reservationDetail);
        }

        Reservation savedReservation = reservationRepository.save(reservation);

        return convertToReservationReseponseDTO(screening, seats, savedReservation);
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
