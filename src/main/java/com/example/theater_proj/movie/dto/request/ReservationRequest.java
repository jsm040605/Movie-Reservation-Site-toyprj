package com.example.theater_proj.movie.dto.request;


import java.util.List;

public record ReservationRequest(
        int screeningId,
        List<Integer> seatsIds,
        int price
) {

}
