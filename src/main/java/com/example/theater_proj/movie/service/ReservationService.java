package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.reservation.*;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@RequiredArgsConstructor
public class ReservationService {
    private final JpaReservationRepository reservationRepository;
    private final JpaScreeningRepository screeningRepository;
    private final JpaSeatRepository seatRepository;

    @Transactional
    public ReservationResponse makeReservations(Long screeningId, List<Long> seatsIds) throws BadRequestException {
        Screening screening = screeningRepository.findById(screeningId).orElseThrow(IllegalArgumentException::new);
        List<Seats> seats = seatRepository.findAllById(seatsIds);

        //예약 상세는 좌석정보, 가격을 가져야 함.
        List<ReservationDetail> reservationDetails = new ArrayList<>();
        for (Seats seat : seats) {
            reservationDetails.add(ReservationDetail.makeReservationDeatil(screening, seat));
        }

        Reservation reservation = Reservation.makeReservation(screening, reservationDetails);
        Reservation savedReservation = reservationRepository.save(reservation);

        return convertToReservationReseponseDTO(screening, seats, savedReservation);
    }

    @Transactional(readOnly = true)
    public ReservationResponse findReservationById(Long reservationId) {
        Reservation reservation = reservationRepository.findById(reservationId).orElseThrow(IllegalArgumentException::new);

        List<Seats> reservedSeats = reservation.getReservedSeats();
        Screening screening = reservation.getScreening();

        return convertToReservationReseponseDTO(screening, reservedSeats, reservation);
    }

    private ReservationResponse convertToReservationReseponseDTO(Screening screening, List<Seats> seats, Reservation reservation) {
        Movie movie = screening.getMovie();
        ReservedMovie movieDTO = ReservedMovie.fromEntity(movie);
        ReservedScreening screeningDTO = ReservedScreening.fromEntity(screening);

        Room room = screening.getRoom();
        ReservedRoom roomDTO = ReservedRoom.fromEntity(room);

        List<ReservedSeats> reservedSeatsDTOS = new ArrayList<>();
        for (Seats seat : seats) {
            ReservedSeats seatsDTO = ReservedSeats.fromEntity(seat);
            reservedSeatsDTOS.add(seatsDTO);
        }

        ReservedInfo reservedInfoDTO = new ReservedInfo(
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
