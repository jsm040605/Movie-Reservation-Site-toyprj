package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.model.Province;
import com.example.theater_proj.movie.entity.Theater;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface JpaTheaterRepository extends JpaRepository<Theater, Long> {
    @Query(
          "select t from Theater t "+
          "where t.province in :provinces"
    )
    List<Theater> findTheaterByProvince(@Param("provinces") List<Province> provinces);
}
