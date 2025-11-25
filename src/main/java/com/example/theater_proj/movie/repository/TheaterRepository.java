package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.Province;
import com.example.theater_proj.movie.entity.Theater;

import java.util.Collection;
import java.util.List;

public interface TheaterRepository {
    public <S extends Theater> List<S> saveAll(Iterable<S> theaters);

    public List<Theater> findByProvinceIn(List<Province> provinces);
}
