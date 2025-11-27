package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.SeatsBookingStatus;
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

    @Enumerated(EnumType.STRING)
    private SeatsBookingStatus bookingStatus;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "screening_id")
    private Screening screening;

    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;
}
