package com.example.theater_proj.movie;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum Province {
    INCHEON("인천광역시"),
    GYEONGI("경기도");

    private String fullName;
}
