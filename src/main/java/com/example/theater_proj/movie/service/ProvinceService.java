package com.example.theater_proj.movie.service;

import com.example.theater_proj.movie.model.Province;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProvinceService {
    public List<String> findAllProvinces(){
        return Arrays.stream(Province.values()).map(Province::getFullName).collect(Collectors.toList());
    }
}
