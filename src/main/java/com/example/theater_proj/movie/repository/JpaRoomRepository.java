package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Room;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaRoomRepository extends JpaRepository<Room, Integer> {
}
