package com.example.theater_proj.global.common.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "영화관 카피 토이 프로젝트의 API 명세서",
                description = "Spring Boot로 개발하는 theater_proj API 명세서입니다.",
                version = "v1.0.0"
        )
)
@Configuration
@RequiredArgsConstructor
public class SwaggerConfig {
    @Bean
    public GroupedOpenApi groupedOpenApi() {
        String [] paths = {
                "/user/**",
                "/api/v1/**"
        };

        return GroupedOpenApi.builder()
                .group("유저 관리와 영화 관리에 대한 API")
                .pathsToMatch(paths)
                .build();
    }
}
