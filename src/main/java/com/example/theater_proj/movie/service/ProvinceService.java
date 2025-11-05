package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.Province;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProvinceService {
    public List<String> findAllProvinces(){
        return Arrays.stream(Province.values()).map(Province::getFullName).collect(Collectors.toList());
    }
}
