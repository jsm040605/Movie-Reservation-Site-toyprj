package com.example.theater_proj.movie.dto.response.screening.list;

import com.example.theater_proj.movie.model.RoomGrade;

import java.util.List;

public record ScreeningInfoIcludedRoom(
        int roomNumber,
        RoomGrade roomGrade,
        List<ScreeningSimpleInfo> screenings
) {
}
