package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;

public interface ScreeningRepository {
    public List<Screening> findScreeningsByCriteria(
            int movieId,
            int theaterId,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );
}
