package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.exception.MovieNotFoundException;
import com.example.theater_proj.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public Movie createMovie(Movie movie){
        return movieRepository.createMovie(movie);
    }

    public Movie findMovieById(int id){
        Optional<Movie> movie = movieRepository.findMovieById(id);

        if (!movie.isPresent()) {
            throw new MovieNotFoundException("id- " + id);
        }
        return movie.get();
    }

    public List<RetrieveAllMoviesDTO> findAllMovies(){
        List<Movie> movies = movieRepository.findAllMovies();

        return movies.stream().map(RetrieveAllMoviesDTO::fromEntity).collect(Collectors.toList());
    }
}
