package com.example.theater_proj.movie.dto.response;

import java.time.LocalDateTime;

public record ReservedScreeningDTO(
        LocalDateTime startTime,
        LocalDateTime endTime
) {
}
