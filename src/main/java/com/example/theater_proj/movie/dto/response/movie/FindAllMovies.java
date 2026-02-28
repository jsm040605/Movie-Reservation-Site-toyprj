package com.example.theater_proj.movie.dto.response.movie;

import com.example.theater_proj.movie.entity.Movie;

public record FindAllMovies(
        Long id,
        String title,
        String genre) {
    //정적 팩토리 메서드 사용
    public static FindAllMovies fromEntity(Movie movie){
        return new FindAllMovies(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
}
