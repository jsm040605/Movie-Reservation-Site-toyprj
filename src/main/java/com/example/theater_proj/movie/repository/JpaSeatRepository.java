package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Seats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaSeatRepository extends JpaRepository<Seats, Integer> {
    public List<Seats> findAllByRoomId(int id);
}
