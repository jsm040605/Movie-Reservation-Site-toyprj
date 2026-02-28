package com.example.theater_proj.movie.entity;

import com.example.theater_proj.movie.model.Province;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Theater {
    @Id
    @GeneratedValue
    @Column(name = "theater_id")
    private Long id;
    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private Province province;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
