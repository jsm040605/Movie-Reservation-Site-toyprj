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
    private Integer id;
    private String title;

    @Column(name = "runtime")
    private Integer runningTime;
    private String genre;
    private String description;

    @Enumerated(EnumType.STRING)
    private AgeRating age_rating;

    public Movie(Integer id, String title, Integer runTime, String genre, String description, AgeRating age_rating) {
        this.id = id;
        this.title = title;
        this.runningTime = runTime;
        this.genre = genre;
        this.description = description;
        this.age_rating = age_rating;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Screening> screeningList;
}
