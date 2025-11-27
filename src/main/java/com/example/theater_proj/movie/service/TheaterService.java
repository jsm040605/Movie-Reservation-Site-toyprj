package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.Province;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private final TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public List<Theater> findTheatersByProvinces(List<String> provinceNames){
        if (provinceNames == null || provinceNames.isEmpty()){
            return Collections.emptyList();
        }

        List<Province> provinces = provinceNames.stream()
                .map(Province::fromFullName)
                .collect(Collectors.toList());

        //이런 데이터 필터링은 최대한 DB가 하게 하는 게 옳다.
        return theaterRepository.findByProvinceIn(provinces);
    }
}
