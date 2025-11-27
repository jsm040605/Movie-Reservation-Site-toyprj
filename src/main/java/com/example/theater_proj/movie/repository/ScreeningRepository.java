package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Screening;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScreeningRepository {
    public <S extends Screening> List<S> saveAll(Iterable<S> screenings);

    public List<Screening> findScreeningsByCriteria(
            int movieId,
            int theaterId,
            LocalDateTime startOfDay,
            LocalDateTime endOfDay
    );

    public Optional<Screening> findScreeningById(int id);
}
