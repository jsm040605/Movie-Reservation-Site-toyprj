package com.example.theater_proj.config;

import com.example.theater_proj.movie.Province;
import com.example.theater_proj.movie.RoomGrade;
import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
@AllArgsConstructor
public class DataLoader implements CommandLineRunner {
    private JpaMovieRepository movieRepository;
    private JpaTheaterRepository theaterRepository;
    private JpaScreeningRepository screeningRepository;
    private JpaRoomRepository roomRepository;
    private JpaSeatRepository seatRepository;

    @Override
    public void run(String... args) throws Exception {
        Movie m1 = new Movie(null, "ababta", "My First Movie", "action", 120);
        Movie m2 = new Movie(null, "demon hunters", "My Second Movie", "animation", 150);
        Movie m3 = new Movie(null, "green books", "My Third Movie", "comic", 150);

        //초기 데이터 생성 로직을 추가해야 함!
        List<Movie> movies = movieRepository.saveAll(Arrays.asList(m1, m2, m3));

        Theater t1 = new Theater(null, "부천", "경기도 부천시 원미구", Province.GYEONGI);
        Theater t2 = new Theater(null, "부평", "인천광역시 부평구", Province.INCHEON);
        Theater t3 = new Theater(null, "소풍", "경기도 부천시 원미구", Province.GYEONGI);

        List<Theater> theaters = theaterRepository.saveAll(Arrays.asList(t1, t2, t3));

        Room r1 = new Room(null, 1, RoomGrade.IMEX, 20000, 20, 10, theaters.get(0));
        Room r2 = new Room(null, 2, RoomGrade.DOLBY, 16000, 15, 10, theaters.get(0));
        Room r3 = new Room(null, 1, RoomGrade.IMEX, 20000, 20, 10, theaters.get(1));

        List<Room> rooms = roomRepository.saveAll(Arrays.asList(r1, r2, r3));
        createSeats(rooms.get(0));
        createSeats(rooms.get(1));
        createSeats(rooms.get(2));

        Screening s1 = new Screening(null, LocalDateTime.parse("2025-10-09T09:00:00"), movies.get(0), rooms.get(0));
        Screening s2 = new Screening(null, LocalDateTime.parse("2025-10-09T09:10:00"), movies.get(0), rooms.get(1));
        Screening s3 = new Screening(null, LocalDateTime.parse("2025-10-09T12:30:00"), movies.get(2), rooms.get(0));
        Screening s4 = new Screening(null, LocalDateTime.parse("2025-10-10T09:00:00"), movies.get(1), rooms.get(2));

        screeningRepository.saveAll(Arrays.asList(s1, s2, s3, s4));
    }

    private void createSeats(Room room) {
        List<Seat> seats = new ArrayList<>();
        for (int i = 0; i < room.getColCount(); i++) {
            for (int j = 0; j < room.getRowCount(); j++){
                Seat seat = new Seat();
                seat.setCol(i);
                seat.setRow(j);
                seat.setRoom(room);
                seats.add(seat);
            }
        }

        seatRepository.saveAll(seats);
    }
}
