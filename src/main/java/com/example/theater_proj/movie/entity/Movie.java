package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.AgeRating;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Movie {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    private Long id;

    private String title;

    @Column(name = "runtime")
    private Integer runningTime;

    private String genre;

    private String description;

    @Enumerated(EnumType.STRING)
    private AgeRating age_rating;
}
