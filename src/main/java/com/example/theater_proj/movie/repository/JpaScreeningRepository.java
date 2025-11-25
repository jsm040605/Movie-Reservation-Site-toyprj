package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Screening;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaScreeningRepository extends JpaRepository<Screening, Integer>, ScreeningRepository {
    @Override
    @Query(
            "select s from Screening s "
            + "join fetch s.room r "
            + "join r.theater t "
            + "where s.movie.id = :movieId "
            + "and t.id = :theaterId "
            + "and s.screeningTime between :startOfDay and :endOfDay"
    )
    List<Screening> findScreeningsByCriteria(
            @Param("movieId") int movieId,
            @Param("theaterId") int theaterId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

}
