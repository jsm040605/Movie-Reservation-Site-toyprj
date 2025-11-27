package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.Province;
import com.example.theater_proj.movie.entity.Theater;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

public class MemoryTheaterRepository implements TheaterRepository{
    private static Map<Integer, Theater> store = new HashMap<>();
    private static Integer sequence = 0;

    static {
        store.put(++sequence, new Theater(sequence, "부천", "경기도 부천시 원미구", Province.GYEONGI));
        store.put(++sequence, new Theater(sequence, "소풍", "경기도 부천시 원미구", Province.GYEONGI));
        store.put(++sequence, new Theater(sequence, "부평", "인천광역시 부평구", Province.INCHEON));
    }

    @Override
    public <S extends Theater> List<S> saveAll(Iterable<S> theaters) {
        return List.of();
    }

    @Override
    public List<Theater> findByProvinceIn(List<Province> provinces) {
        return List.of();
    }


}
