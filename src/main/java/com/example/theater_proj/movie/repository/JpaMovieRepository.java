package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Movie;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface JpaMovieRepository extends JpaRepository<Movie, Integer>, MovieRepository {

}
