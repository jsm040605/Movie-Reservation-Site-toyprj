package com.example.theater_proj.movie.dto.response.reservation;

import com.example.theater_proj.movie.entity.Movie;

public record ReservedMovie(
        Long movieId,
        String title,
        Integer runningTime
) {
    public static ReservedMovie fromEntity(Movie movie){
        return new ReservedMovie(
                movie.getId(),
                movie.getTitle(),
                movie.getRunningTime()
        );
    }
}
