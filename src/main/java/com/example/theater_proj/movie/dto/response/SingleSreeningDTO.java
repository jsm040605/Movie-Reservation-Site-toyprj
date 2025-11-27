package com.example.theater_proj.movie.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

public record SingleSreeningDTO(
        int id,
        LocalDateTime startTime,
        long reservableSeatsCount
) {

}
