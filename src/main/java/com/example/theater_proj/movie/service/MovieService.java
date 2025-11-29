package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.exception.MovieNotFoundException;
import com.example.theater_proj.movie.repository.JpaMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private JpaMovieRepository movieRepository;

    public MovieService(JpaMovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie findMovieById(int id){
        Optional<Movie> movie = movieRepository.findById(id);

        if (!movie.isPresent()) {
            throw new MovieNotFoundException("id- " + id);
        }
        return movie.get();
    }

    public List<RetrieveAllMoviesDTO> findAllMovies(){
        List<Movie> movies = movieRepository.findAll();

        return movies.stream().map(RetrieveAllMoviesDTO::fromEntity).collect(Collectors.toList());
    }
}
