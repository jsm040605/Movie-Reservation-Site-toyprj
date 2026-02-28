package com.example.theater_proj.movie.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
@Entity
public class Screening {
    @Id
    @GeneratedValue
    @Column(name = "screening_id")
    private Long id;

    @Column(name = "screening_time")
    private LocalDateTime screeningTime;

    private int remainQuantity;

    private int price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "room_id")
    private Room room;

    public Screening(Long id, LocalDateTime screeningTime, int remainQuantity, Movie movie, Room room) {
        this.id = id;
        this.screeningTime = screeningTime;
        this.remainQuantity = remainQuantity;
        this.movie = movie;
        this.room = room;
    }

    @OneToMany(mappedBy = "screening")
    private List<ReservationDetail> reservationDetails = new ArrayList<>();

    //연관관계 mapping method
    public void addReservationDetail(ReservationDetail reservationDetail) {
        reservationDetails.add(reservationDetail);
    }

    //business Method
    public void increaseSeatsCount(int canceledQuantity){
        remainQuantity += remainQuantity;
    }

    public void reduceSeatsCount(int reservedQuantity) {
        remainQuantity -= reservedQuantity;
    }

    public LocalDateTime calculateEndTime(){
        return screeningTime.plusMinutes(movie.getRunningTime());
    }

    //일치하는 예약정보를 찾는 로직
    public boolean checkReservedSeats(Seats seat) {
        return reservationDetails.stream()
                .anyMatch(reservationDetail ->
                        reservationDetail.getSeat().getId().equals(seat.getId())
                );
    }

}
