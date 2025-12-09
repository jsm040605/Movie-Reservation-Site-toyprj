package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.model.Province;
import com.example.theater_proj.movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaTheaterRepository extends JpaRepository<Theater, Integer> {
    List<Theater> findByProvinceIn(List<Province> provinces);
}
