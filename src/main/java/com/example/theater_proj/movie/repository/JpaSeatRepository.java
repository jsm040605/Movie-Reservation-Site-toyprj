package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSeatRepository extends JpaRepository<Seat, Integer> {
    public List<Seat> findAllByRoomId(int id);
}
