package com.example.theater_proj.movie;

import com.example.theater_proj.movie.dto.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.entity.Movie;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.service.MovieService;
import com.example.theater_proj.movie.service.ProvinceService;
import com.example.theater_proj.movie.service.TheaterService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class MovieController {
    MovieService movieService;
    TheaterService theaterService;
    ProvinceService provinceService;

    public MovieController(MovieService movieService, TheaterService theaterService, ProvinceService provinceService) {
        this.movieService = movieService;
        this.theaterService = theaterService;
        this.provinceService = provinceService;
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
}
