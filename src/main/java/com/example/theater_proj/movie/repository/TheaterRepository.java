package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Theater;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository {
    public List<Theater> findAll();
}
