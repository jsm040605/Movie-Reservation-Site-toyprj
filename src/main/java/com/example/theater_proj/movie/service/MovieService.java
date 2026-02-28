package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.movie.FindAllMovies;
import com.example.theater_proj.movie.dto.response.movie.RetrieveMovieResponse;
import com.example.theater_proj.movie.dto.response.movie.AllMovieResponse;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.exception.MovieNotFoundException;
import com.example.theater_proj.movie.repository.JpaMovieRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MovieService {
    private final JpaMovieRepository movieRepository;

    public RetrieveMovieResponse findMovieById(Long id){
        Movie findMovie = movieRepository.findById(id).orElseThrow(() -> new MovieNotFoundException("movie not found"));

        return RetrieveMovieResponse.fromEntity(findMovie);
    }

    public AllMovieResponse findAllMovies(){
        List<Movie> movies = movieRepository.findAll();

        return new AllMovieResponse(movies.stream().map(FindAllMovies::fromEntity).collect(Collectors.toList()));
    }
}
