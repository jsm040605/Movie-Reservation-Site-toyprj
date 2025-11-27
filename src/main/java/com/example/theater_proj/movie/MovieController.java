package com.example.theater_proj.movie;

import com.example.theater_proj.movie.dto.request.ReservationRequest;
import com.example.theater_proj.movie.dto.response.ReservationResponse;
import com.example.theater_proj.movie.dto.response.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.dto.response.RetrieveScreeningDTO;
import com.example.theater_proj.movie.dto.response.RoomScreeningDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.entity.Reservation;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.service.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    MovieService movieService;
    TheaterService theaterService;
    ProvinceService provinceService;
    ScreeningService screeningService;
    ReservationService reservationService;

    public MovieController(
            MovieService movieService,
            TheaterService theaterService,
            ProvinceService provinceService,
            ScreeningService screeningService,
            ReservationService reservationService
    ) {
        this.movieService = movieService;
        this.theaterService = theaterService;
        this.provinceService = provinceService;
        this.screeningService = screeningService;
        this.reservationService = reservationService;
    }

    //영화관 목록 조회
    @GetMapping("/movies")
    public ResponseEntity<List<RetrieveAllMoviesDTO>> retrieveAllMovies(){
        List<RetrieveAllMoviesDTO> movies = movieService.findAllMovies();
        return ResponseEntity.ok(movies);
    }

    //영화 상세 조회
    @GetMapping("/movies/{id}")
    public Movie retrieveMovieById(@PathVariable int id) {
        return movieService.findMovieById(id);
    }

    //지역 조회
    @GetMapping("/provinces")
    public List<String> retrieveAllProvinces(){
        return provinceService.findAllProvinces();
    }

    //영화관 조회
    @GetMapping("/theaters")
    public List<Theater> retreiveTheatersByProvince(@RequestParam("province") List<String> provinces){
        return theaterService.findTheatersByProvinces(provinces);
    }
    
    //상영 조회
    @GetMapping("/screenings")
    public List<RoomScreeningDTO> retrieveScreeningsByCriteria(
            @RequestParam("movie_id") int movie_id,
            @RequestParam("theater_id") int theater_id,
            @RequestParam("date")
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
            LocalDate date
    ){
        return screeningService.findScreeningsByMovieTheaterDate(movie_id, theater_id, date);
    }
    
    //특정 상영 조회
    @GetMapping("/screenings/{id}")
    public RetrieveScreeningDTO retrieveRoomByScreening(@PathVariable int id){
        return screeningService.getSeatMap(id);
    }

    //예약 생성
    //예약은 어떤 영화, 영화관, 관, 상영, 좌석 모든 정보를 다 가지고 있어야 하는 거 아닌가?
    @PostMapping("/reservations")
    public ReservationResponse makeReservation(@RequestBody ReservationRequest request){
        return reservationService.makeReservations(request.screeningId(), request.seatsIds(), request.price());
    }
}
