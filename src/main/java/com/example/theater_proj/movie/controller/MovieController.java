package com.example.theater_proj.movie.controller;

import com.example.theater_proj.movie.dto.response.movie.AllMovieResponse;
import com.example.theater_proj.movie.dto.response.movie.RetrieveMovieResponse;
import com.example.theater_proj.movie.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class MovieController {
    private final MovieService movieService;

    //영화 목록 조회
    @GetMapping("/movies")
    public AllMovieResponse retrieveAllMovies(){
        AllMovieResponse movies = movieService.findAllMovies();
        return movies;
    }

    //영화 상세 조회
    @GetMapping("/movies/{id}")
    public RetrieveMovieResponse retrieveMovieById(@PathVariable Long id) {
        return movieService.findMovieById(id);
    }

}
