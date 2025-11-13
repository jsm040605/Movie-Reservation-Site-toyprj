package com.example.theater_proj.user.model;

import lombok.Getter;

@Getter
public enum UserGrade {
    BRONZE(1, "BRONZE", 0.01),
    SILVER(2, "SILVER", 0.03),
    GOLD(3, "GOLD", 0.05),
    VIP(4, "VIP", 0.07);

    private final int level;
    private final String description;
    private final double benefitRate;

    UserGrade(int level, String description, double benefitRate) {
        this.level = level;
        this.description = description;
        this.benefitRate = benefitRate;
    }
}
