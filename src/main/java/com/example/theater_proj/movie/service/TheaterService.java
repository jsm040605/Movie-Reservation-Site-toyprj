package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.dto.response.theater.AllTheatersResponse;
import com.example.theater_proj.movie.dto.response.theater.TheaterListResponse;
import com.example.theater_proj.movie.model.Province;
import com.example.theater_proj.movie.entity.Theater;
import com.example.theater_proj.movie.repository.JpaTheaterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class TheaterService {
    private final JpaTheaterRepository theaterRepository;

    public AllTheatersResponse findTheatersByProvinces(List<String> provinceNames){
        if (provinceNames == null || provinceNames.isEmpty()){
            return new AllTheatersResponse(Collections.emptyList());
        }

        //(type casting) String -> Province
        List<Province> provinces = provinceNames.stream()
                .map(Province::fromFullName)
                .collect(Collectors.toList());

        List<Theater> theaters = theaterRepository.findTheaterByProvince(provinces);

        return new AllTheatersResponse(this.convertDTO(theaters));
    }

    private List<TheaterListResponse> convertDTO(List<Theater> theaters){
        return theaters.stream().map(TheaterListResponse::fromEntity).collect(Collectors.toList());
    }
}
