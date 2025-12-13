package com.example.theater_proj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TheaterProjApplication {

    public static void main(String[] args) {
        SpringApplication.run(TheaterProjApplication.class, args);
    }

}
