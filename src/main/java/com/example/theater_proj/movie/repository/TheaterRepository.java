package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Theater;

import java.util.List;
import java.util.Optional;

public interface TheaterRepository {
    public <S extends Theater> List<S> saveAll(Iterable<S> theaters);

    public List<Theater> findAll();
}
