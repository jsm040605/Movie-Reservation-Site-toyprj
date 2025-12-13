package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.entity.Theater;

public record TheaterListResponse(
        Integer theater_id,
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
