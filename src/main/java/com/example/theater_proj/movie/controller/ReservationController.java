package com.example.theater_proj.movie.controller;

import com.example.theater_proj.movie.dto.request.ReservationRequest;
import com.example.theater_proj.movie.dto.response.reservation.ReservationResponse;
import com.example.theater_proj.movie.service.ReservationService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class ReservationController {
    private final ReservationService reservationService;

    //예약 생성
    //예약은 어떤 영화, 영화관, 관, 상영, 좌석 모든 정보를 다 가지고 있어야 하는 거 아닌가?
    @PostMapping("/reservations")
    public ResponseEntity<ReservationResponse> makeReservation(@RequestBody ReservationRequest request) throws BadRequestException {
        ReservationResponse reservationResponse = reservationService.makeReservations(request.screeningId(), request.seatsIds());

        return new ResponseEntity<>(reservationResponse, HttpStatus.CREATED);
    }

    //예약 조회
    @GetMapping("/reservations/{id}")
    public ReservationResponse retrieveReservationById(@PathVariable Long id){
        return reservationService.findReservationById(id);
    }
}
