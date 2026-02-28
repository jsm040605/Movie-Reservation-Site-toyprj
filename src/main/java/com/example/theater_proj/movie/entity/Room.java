package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.RoomGrade;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
public class Room {
    @Id
    @GeneratedValue
    @Column(name = "room_id")
    private Long id;

    private Integer roomNumber;

    @Enumerated(EnumType.STRING)
    private RoomGrade roomGrade;

    private Integer rowCount;

    private Integer colCount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "theater_id")
    private Theater theater;

    public Room(Long id, Integer roomNumber, RoomGrade roomGrade, Integer rowCount, Integer colCount, Theater theater) {
        this.id = id;
        this.roomNumber = roomNumber;
        this.roomGrade = roomGrade;
        this.rowCount = rowCount;
        this.colCount = colCount;
        this.theater = theater;
    }

    @OneToMany(mappedBy = "room", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seats> seats = new ArrayList<>();
}
