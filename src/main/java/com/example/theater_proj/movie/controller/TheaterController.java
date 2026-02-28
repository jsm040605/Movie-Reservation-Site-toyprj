package com.example.theater_proj.movie.controller;

import com.example.theater_proj.movie.dto.response.theater.AllTheatersResponse;
import com.example.theater_proj.movie.dto.response.theater.TheaterListResponse;
import com.example.theater_proj.movie.service.ProvinceService;
import com.example.theater_proj.movie.service.TheaterService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1")
public class TheaterController {
    private final TheaterService theaterService;
    private final ProvinceService provinceService;


    //지역 조회
    @GetMapping("/provinces")
    public List<String> retrieveAllProvinces(){
        return provinceService.findAllProvinces();
    }

    //영화관 조회
    @GetMapping("/theaters")
    public AllTheatersResponse retreiveTheatersByProvince(@RequestParam("province") List<String> provinces){
        return theaterService.findTheatersByProvinces(provinces);
    }


}
