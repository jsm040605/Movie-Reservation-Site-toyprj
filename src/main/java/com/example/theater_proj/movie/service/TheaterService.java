package com.example.theater_proj.movie.service;

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

    public List<Theater> findTheatersByProvinces(List<String> provinces){
        if (provinces == null || provinces.isEmpty()){
            return Collections.emptyList();
        }

        //이런 데이터 필터링은 최대한 DB가 하게 하는 게 옳다.
        List<Theater> theaters = theaterRepository.findAll();

        return theaters.stream().filter(
                theater -> theater.getProvince() != null &&
                        provinces.contains(theater.getProvince().getFullName())
        ).collect(Collectors.toList());
    }
}
