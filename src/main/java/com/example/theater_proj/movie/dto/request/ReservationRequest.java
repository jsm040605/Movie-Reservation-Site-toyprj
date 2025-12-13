package com.example.theater_proj.movie.dto.request;


import com.example.theater_proj.movie.model.PaymentStatus;

import java.util.List;

public record ReservationRequest(
        int screeningId,
        List<Integer> seatsIds,
        int price
) {

}
