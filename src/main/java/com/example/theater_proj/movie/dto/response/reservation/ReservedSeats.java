package com.example.theater_proj.movie.dto.response.reservation;

import com.example.theater_proj.movie.entity.Seats;

public record ReservedSeats(
        Integer row,
        Integer col
){
    public static ReservedSeats fromEntity(Seats seat){
        return new ReservedSeats(
                seat.getRow(),
                seat.getCol()
        );
    }
}
