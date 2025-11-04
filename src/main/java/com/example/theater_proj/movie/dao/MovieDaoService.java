package com.example.theater_proj.movie.dao;

import com.example.theater_proj.movie.dto.RetrieveAllMoviesDTO;
import com.example.theater_proj.movie.entity.Movie;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class MovieDaoService {
    private static Map<Integer, Movie> store = new HashMap<>();
    private static Integer sequence = 0;

    static {
        store.put(++sequence, new Movie(sequence, "abata", "My first Movie", "action"));
        store.put(++sequence, new Movie(sequence, "demon hunters", "My Second Movie", "animation"));
        store.put(++sequence, new Movie(sequence, "green books", "My Third Movie", "comic"));
    }

    public Movie createMovie(Movie movie) {
        movie.setId(++sequence);
        store.put(movie.getId(), movie);
        return movie;
    }

    public Optional<Movie> findMovieById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    public List<RetrieveAllMoviesDTO> findAllMovie() {
        List<RetrieveAllMoviesDTO> dtos = store
                .values()
                .stream()
                .map(RetrieveAllMoviesDTO::fromEntity)
                .collect(Collectors.toList());
        return dtos;
    }
}
