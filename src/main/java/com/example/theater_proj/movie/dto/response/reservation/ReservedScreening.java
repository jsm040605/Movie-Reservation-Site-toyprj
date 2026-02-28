package com.example.theater_proj.movie.dto.response.reservation;

import com.example.theater_proj.movie.entity.Room;
import com.example.theater_proj.movie.entity.Screening;

import java.time.LocalDateTime;

public record ReservedScreening(
        LocalDateTime startTime,
        LocalDateTime endTime
) {
    public static ReservedScreening fromEntity(Screening screening){
        return new ReservedScreening(
                screening.getScreeningTime(),
                screening.calculateEndTime()
        );
    }

}
