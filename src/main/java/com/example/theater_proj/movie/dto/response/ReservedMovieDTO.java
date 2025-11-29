package com.example.theater_proj.movie.dto.response;

public record ReservedMovieDTO(
        Integer movieId,
        String movieName,
        Integer runningTime
) {
}
