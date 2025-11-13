package com.example.theater_proj.movie.exception;

import org.apache.logging.log4j.message.Message;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class TheaterNotFoundException extends Exception{
    public TheaterNotFoundException(String message){
        super(message);
    }
}
