package com.example.theater_proj.movie;

import com.example.theater_proj.movie.dto.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.exception.MovieNotFoundException;
import com.example.theater_proj.movie.dao.MovieDaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
public class MovieController {
    MovieDaoService service;

    public MovieController(MovieDaoService service) {
        this.service = service;
    }

    //영화관 목록 조회
    @GetMapping("/movies")
    public ResponseEntity<List<RetrieveAllMoviesDTO>> retrieveAllMovies(){
        List<RetrieveAllMoviesDTO> movies = service.findAllMovie();
        return ResponseEntity.ok(movies);
    }

    //영화 상세 조회
    @GetMapping("/movies/{id}")
    public Movie retrieveMovieById(@PathVariable int id) {
        Optional<Movie> movie = service.findMovieById(id);
        if (movie.isEmpty()) {
            throw new MovieNotFoundException("id- " + id);
        }

        return movie.get();
    }
}
