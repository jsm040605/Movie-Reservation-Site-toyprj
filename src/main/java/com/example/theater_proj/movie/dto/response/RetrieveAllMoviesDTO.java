package com.example.theater_proj.movie.dto.response;

import com.example.theater_proj.movie.entity.Movie;

public record RetrieveAllMoviesDTO(
        Integer id,
        String title,
        String genre) {
    //정적 팩토리 메서드 사용
    public static RetrieveAllMoviesDTO fromEntity(Movie movie){
        return new RetrieveAllMoviesDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
}
