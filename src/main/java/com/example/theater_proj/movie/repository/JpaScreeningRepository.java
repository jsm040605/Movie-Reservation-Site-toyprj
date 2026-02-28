package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Screening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface JpaScreeningRepository extends JpaRepository<Screening, Long> {
    @Query(
            "select s from Screening s "
                    + "join fetch s.room r "
                    + "join fetch s.movie m "
                    + "join fetch r.theater t "
                    + "where m.id = :movieId "
                    + "and t.id = :theaterId "
                    + "and s.screeningTime between :startOfDay and :endOfDay "
                    + "order by s.screeningTime"
    )
    List<Screening> findScreeningsByCriteria(
            @Param("movieId") Long movieId,
            @Param("theaterId") Long theaterId,
            @Param("startOfDay") LocalDateTime startOfDay,
            @Param("endOfDay") LocalDateTime endOfDay
    );

    @Query(
            "select s from Screening s "+
                    "join fetch s.room r "+
                    "where s.id = :screening_id"
    )
    Optional<Screening> findByIdWithDetail(@Param("screening_id") Long screeningId);
}
