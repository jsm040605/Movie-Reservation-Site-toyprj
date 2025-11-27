package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Room;

import java.util.List;
import java.util.Optional;

public interface RoomRepository {
    public <S extends Room> List<S> saveAll(Iterable<S> rooms);

    public Room findById(int id);
}
