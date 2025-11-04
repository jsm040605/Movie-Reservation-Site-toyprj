package com.example.theater_proj.movie.dto;

import com.example.theater_proj.movie.entity.Movie;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RetrieveAllMoviesDTO {
    private Integer id;
    private String title;
    private String genre;

    //정적 팩토리 메서드 사용
    public static RetrieveAllMoviesDTO fromEntity(Movie movie){
        return new RetrieveAllMoviesDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getGenre()
        );
    }
}
