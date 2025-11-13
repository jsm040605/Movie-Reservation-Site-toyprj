package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Movie;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMovieRepository implements MovieRepository {
    private static Map<Integer, Movie> store = new HashMap<>();
    private static Integer sequence = 0;

    static {
        store.put(++sequence, new Movie(sequence, "abata", "My first Movie", "action"));
        store.put(++sequence, new Movie(sequence, "demon hunters", "My Second Movie", "animation"));
        store.put(++sequence, new Movie(sequence, "green books", "My Third Movie", "comic"));
    }


    @Override
    public Optional<Movie> findMovieById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Movie> findAll() {
        return new ArrayList<>(store.values());
    }
}
