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
public class Reservation {
    @Id
    @GeneratedValue
    private int id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "screening_id")
    private Screening screening;

    public Reservation(int id, Screening screening) {
        this.id = id;
        this.screening = screening;
    }

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;
}
