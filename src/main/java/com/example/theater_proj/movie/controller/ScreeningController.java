package com.example.theater_proj.movie.controller;

import com.example.theater_proj.movie.dto.response.screening.list.AllScreeningsResponse;
import com.example.theater_proj.movie.dto.response.screening.retrieve.RetrieveScreeningResponse;
import com.example.theater_proj.movie.service.ScreeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1")
public class ScreeningController {

    private final ScreeningService screeningService;

    //상영 조회
    @GetMapping("/screenings")
    public AllScreeningsResponse retrieveScreeningsByCriteria(
            @RequestParam("movie_id") Long movie_id,
            @RequestParam("theater_id") Long theater_id,
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ){
        return screeningService.findScreeningsByMovieTheaterDate(movie_id, theater_id, date);
    }

    //특정 상영 조회
    @GetMapping("/screenings/{id}")
    public RetrieveScreeningResponse retrieveRoomByScreening(@PathVariable Long id){
        return screeningService.getSeatMap(id);
    }
}
