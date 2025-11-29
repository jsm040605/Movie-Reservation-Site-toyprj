package com.example.theater_proj.movie.dto.response;

import java.util.List;

//영화 이름, 관 번호, 관 등급, 예약 정보, 좌석 정보
public record ReservationResponse(
    ReservedMovieDTO movie,
    ReservedScreeningDTO screening,
    ReservedRoomDTO room,
    List<ReservedSeatsDTO> seats,
    ReservedInfoDTO info
) {
}
