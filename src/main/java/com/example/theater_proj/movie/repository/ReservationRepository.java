package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Reservation;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository {
    public Reservation save(Reservation reservation);

    public List<Reservation> findAllByScreeningId(int id);
}
