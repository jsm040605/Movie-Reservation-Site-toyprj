package com.example.theater_proj.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

@AllArgsConstructor
@Getter
public enum Province {
    INCHEON("인천광역시"),
    GYEONGI("경기도");

    private String fullName;

    public static Province fromFullName(String provinceName){
        return Arrays.stream(Province.values())
                .filter(p -> p.getFullName().equals(provinceName))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("잘못된 지역명 입니다."));
    }
}
