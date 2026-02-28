package com.example.theater_proj.global.common.config;

import com.example.theater_proj.movie.entity.*;
import com.example.theater_proj.movie.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner; // 추가
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional; // jakarta 대신 spring 추천

import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner { // 1. CommandLineRunner 구현

    private final InitService initService;

    @Override
    public void run(String... args) throws Exception {
        initService.initdb1();
    }

    @Component
    @Transactional
    @RequiredArgsConstructor
    static class InitService {
        private final JpaRoomRepository roomRepository;
        private final JpaSeatRepository seatRepository;

        public void initdb1() {
            if (seatRepository.count() > 0) return;

            List<Room> allRooms = roomRepository.findAll();
            for (Room room : allRooms) {
                createSeats(room);
            }
        }

        private void createSeats(Room room) {
            List<Seats> seats = new ArrayList<>();
            for (int i = 0; i < room.getColCount(); i++) {
                for (int j = 0; j < room.getRowCount(); j++) {
                    Seats seat = new Seats();
                    seat.setCol(i);
                    seat.setRow(j);
                    seat.setRoom(room);
                    seats.add(seat);
                }
            }
            seatRepository.saveAll(seats);
        }
    }
}