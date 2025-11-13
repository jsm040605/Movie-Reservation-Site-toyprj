package com.example.theater_proj.config;

import com.example.theater_proj.movie.repository.MemoryMovieRepository;
import com.example.theater_proj.movie.repository.MemoryTheaterRepository;
import com.example.theater_proj.movie.repository.MovieRepository;
import com.example.theater_proj.movie.repository.TheaterRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringConfig {
    @Bean
    public MovieRepository movieRepository(){
        return new MemoryMovieRepository();
    }

    @Bean
    public TheaterRepository theaterRepository(){
        return new MemoryTheaterRepository();
    }
}
