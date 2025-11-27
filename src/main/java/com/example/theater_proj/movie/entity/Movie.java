package com.example.theater_proj.movie.entity;

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
    private Integer id;
    private String title;
    private String description;
    private String genre;

    private Integer runningTime;

    public Movie(Integer id, String title, String description, String genre, Integer runningTime) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.genre = genre;
        this.runningTime = runningTime;
    }

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Screening> screeningList;
}
