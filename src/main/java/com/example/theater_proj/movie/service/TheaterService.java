package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.TheaterListResponse;
import com.example.theater_proj.movie.model.Province;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.repository.JpaTheaterRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private final JpaTheaterRepository theaterRepository;

    public TheaterService(JpaTheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<TheaterListResponse> findTheatersByProvinces(List<String> provinceNames){
        if (provinceNames == null || provinceNames.isEmpty()){
            return Collections.emptyList();
        }

        List<Province> provinces = provinceNames.stream()
                .map(Province::fromFullName)
                .collect(Collectors.toList());

        List<Theater> theaters = theaterRepository.findByProvinceIn(provinces);

        return this.convertDTO(theaters);
    }

    private List<TheaterListResponse> convertDTO(List<Theater> theaters){
        return theaters.stream().map(TheaterListResponse::fromEntity).collect(Collectors.toList());
    }
}
