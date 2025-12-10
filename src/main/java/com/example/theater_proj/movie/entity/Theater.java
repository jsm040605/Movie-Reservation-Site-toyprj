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
    private Integer id;
    private String name;
    private String address;

    @Enumerated(EnumType.STRING)
    private Province province;

    private LocalDateTime created_at;
    private LocalDateTime updated_at;

    public Theater(Integer id, String name, String address, Province province, LocalDateTime created_at, LocalDateTime updated_at) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.province = province;
        this.created_at = created_at;
        this.updated_at = updated_at;
    }

    @OneToMany(mappedBy = "theater", fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Room> rooms;
}
