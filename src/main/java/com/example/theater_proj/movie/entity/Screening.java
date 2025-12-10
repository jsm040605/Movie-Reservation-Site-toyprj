package com.example.theater_proj.movie.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Screening {
    @Id
    @GeneratedValue
    @Column(name = "screening_id")
    private Integer id;

    @Column(name = "start_time")
    private LocalDateTime startTime;

    @Column(name = "end_time")
    private LocalDateTime endTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    @JsonIgnore
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    @JsonIgnore
    private Room room;

    public Screening(Integer id, LocalDateTime startTime, Movie movie, Room room) {
        this.id = id;
        this.startTime = startTime;
//        this.endTime = calculateEndTime(startTime, movie.getRunningTime());
        this.movie = movie;
        this.room = room;
    }

    @PrePersist
    @PreUpdate
    public void caculateEndTime(){
        if (this.startTime != null && this.movie != null && this.room != null){
            this.endTime = this.startTime.plusMinutes(movie.getRunningTime());
        }
    }

    @OneToMany(mappedBy = "screening", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Reservation> reservations;
}
