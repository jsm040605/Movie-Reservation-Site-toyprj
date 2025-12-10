package com.example.theater_proj.global.common.auth;

import com.example.theater_proj.global.common.model.TokenType;
import com.example.theater_proj.global.jwt.JwtUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@RequiredArgsConstructor
public class AuthInterceptor implements HandlerInterceptor {

    private final JwtUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {

        String token = extractBearer(request);

        if(!jwtUtil.validate(token))
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "유효하지 않은 토큰입니다.");


        if(jwtUtil.getTokenType(token) != TokenType.ACCESS)
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Access Token이 필요합니다.");

        request.setAttribute("username", jwtUtil.getUsername(token));

        return true;
    }

    //헤더로부터 Bearer 토큰 추출
    private String extractBearer(HttpServletRequest request) {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (!StringUtils.hasText(header) || !header.startsWith("Bearer ")) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Authorization 헤더 전송 형식이 잘못되었습니다.");
        }
        return header.substring(7);
    }
}
