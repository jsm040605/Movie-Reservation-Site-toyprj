package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaSeatRepository extends JpaRepository<Seat, Integer>, SeatRepository {
}
