package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.repository.TheaterRepository;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TheaterService {
    private TheaterRepository theaterRepository;

    public TheaterService(TheaterRepository theaterRepository) {
        this.theaterRepository = theaterRepository;
    }

    public Theater createTheater(Theater theater) {
        return theaterRepository.createTheater(theater);
    }

    public List<Theater> findTheatersByProvinces(List<String> provinces){
        if (provinces == null || provinces.isEmpty()){
            return Collections.emptyList();
        }

        List<Theater> theaters = theaterRepository.findAllTheaters();

        return theaters.stream().filter(
                theater -> theater.getProvince() != null &&
                        provinces.contains(theater.getProvince().getFullName())
        ).collect(Collectors.toList());
    }
}
