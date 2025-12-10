package com.example.theater_proj.user.entity;

import com.example.theater_proj.global.common.model.Status;
import com.example.theater_proj.user.model.UserGrade;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@JsonIgnoreProperties(value = {"password", "phoneNumber"})
@Schema(description = "사용자 상세 정보를 위한 도메인 객체")
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String password;
    private String name;
    private String phoneNumber;
    private UserGrade userGrade;

    @Enumerated(EnumType.STRING)
    @Column(name = "user_status")
    private Status status;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public User(String email, String password, String name, String phoneNumber) {

        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.status = Status.ACTIVE;
        this.userGrade = UserGrade.BRONZE;
        this.createdAt = new Timestamp(System.currentTimeMillis());
        this.updatedAt = new Timestamp(System.currentTimeMillis());

    }
}
