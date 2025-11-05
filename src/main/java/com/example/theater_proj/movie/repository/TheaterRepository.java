package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Theater;

import java.util.List;

public interface TheaterRepository {
    public Theater createTheater(Theater theater);

    public List<Theater> findAllTheaters();
}
