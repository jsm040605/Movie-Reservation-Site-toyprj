package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Theater;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaTheaterRepository extends JpaRepository<Theater, Integer>, TheaterRepository {
}
