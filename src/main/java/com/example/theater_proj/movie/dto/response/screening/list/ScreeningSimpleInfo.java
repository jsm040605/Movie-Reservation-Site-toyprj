package com.example.theater_proj.movie.dto.response.screening.list;

import com.example.theater_proj.movie.entity.Screening;

import java.time.LocalDateTime;

public record ScreeningSimpleInfo(
        Long id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        int reservedSeatsCount
) {
    public static ScreeningSimpleInfo fromEntity(Screening screening){
        return new ScreeningSimpleInfo(
                screening.getId(),
                screening.getScreeningTime(),
                screening.calculateEndTime(),
                screening.getRemainQuantity()
        );
    }
}
