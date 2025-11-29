package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.Province;
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

    public List<Theater> findTheatersByProvinces(List<String> provinceNames){
        if (provinceNames == null || provinceNames.isEmpty()){
            return Collections.emptyList();
        }

        List<Province> provinces = provinceNames.stream()
                .map(Province::fromFullName)
                .collect(Collectors.toList());

        return theaterRepository.findByProvinceIn(provinces);
    }
}
