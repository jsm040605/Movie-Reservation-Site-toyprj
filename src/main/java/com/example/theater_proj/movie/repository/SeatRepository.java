package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Seat;

import java.util.List;
import java.util.Optional;

public interface SeatRepository {
    public <S extends Seat> List<S> saveAll(Iterable<S> seats);

    public List<Seat> findAllByRoomId(int id);
}
