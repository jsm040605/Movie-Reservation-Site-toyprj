package com.example.theater_proj.config;

import com.example.theater_proj.movie.repository.JpaMovieRepository;
import com.example.theater_proj.movie.repository.JpaTheaterRepository;
import com.example.theater_proj.movie.repository.MovieRepository;
import com.example.theater_proj.movie.repository.TheaterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MovieRepository movieRepository(JpaMovieRepository jpaMovieRepository) {
        return jpaMovieRepository;
    }

    @Bean
    public TheaterRepository theaterRepository(JpaTheaterRepository jpaTheaterRepository) {
        return jpaTheaterRepository;
    }
}
