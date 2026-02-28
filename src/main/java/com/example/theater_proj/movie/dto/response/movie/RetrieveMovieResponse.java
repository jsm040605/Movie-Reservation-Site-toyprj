package com.example.theater_proj.movie.dto.response.movie;

import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.model.AgeRating;

public record RetrieveMovieResponse(
        Long id,
        String title,
        int running_time,
        String genre,
        String description,
        AgeRating ageRating
) {
    public static RetrieveMovieResponse fromEntity(Movie movie){
        return new RetrieveMovieResponse(
                movie.getId(),
                movie.getTitle(),
                movie.getRunningTime(),
                movie.getGenre(),
                movie.getDescription(),
                movie.getAge_rating()
        );
    }
}
