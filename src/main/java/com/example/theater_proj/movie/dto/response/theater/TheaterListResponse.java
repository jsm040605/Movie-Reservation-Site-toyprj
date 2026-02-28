package com.example.theater_proj.movie.dto.response.theater;

import com.example.theater_proj.movie.entity.Theater;

public record TheaterListResponse(
        Long theater_id,
        String name,
        String address
) {
    public static TheaterListResponse fromEntity(Theater theater){
        return new TheaterListResponse(
                theater.getId(),
                theater.getName(),
                theater.getAddress()
        );
    }
}
