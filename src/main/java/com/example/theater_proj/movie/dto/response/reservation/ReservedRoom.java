package com.example.theater_proj.movie.dto.response.reservation;

import com.example.theater_proj.movie.entity.Room;
import com.example.theater_proj.movie.model.RoomGrade;

public record ReservedRoom(
        Integer roomNumber,
        RoomGrade roomGrade
) {
    public static ReservedRoom fromEntity(Room room){
        return new ReservedRoom(
                room.getRoomNumber(),
                room.getRoomGrade()
        );
    }
}
