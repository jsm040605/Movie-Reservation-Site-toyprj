package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieRepository {
    public Optional<Movie> findMovieById(int id);

    public List<Movie> findAll();
}
