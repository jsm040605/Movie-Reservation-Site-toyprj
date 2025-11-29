package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JpaReservationRepository extends JpaRepository<Reservation, Integer>{
}
