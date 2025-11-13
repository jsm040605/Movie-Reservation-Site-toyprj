package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Movie;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaMovieRepository extends JpaRepository<Movie, Integer>, MovieRepository {
}
