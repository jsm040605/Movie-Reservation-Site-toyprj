package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.RoomGrade;
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
public class Room {
    @Id
    @GeneratedValue
    private Integer id;
    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomGrade roomGrade;
    private Integer price;
    private Integer rowCount;
    private Integer colCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Room(Integer id, Integer roomNumber, RoomGrade roomGrade, Integer price, Integer rowCount, Integer colCount, Theater theater) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomGrade = roomGrade;
        this.price = price;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.theater = theater;
    }

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Seat> seats;

    @OneToMany(mappedBy = "room", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Screening> screeningList;
}
