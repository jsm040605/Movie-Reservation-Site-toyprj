package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.Province;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Theater {
    private Integer id;
    private String name;
    private String address;
    private Province province;
}
