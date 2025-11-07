package com.example.theater_proj.movie.repository;

import com.example.theater_proj.movie.entity.Movie;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class MemoryMovieRepository implements MovieRepository {
    private static Map<Integer, Movie> store = new HashMap<>();
    private static Integer sequence = 0;

    static {
        store.put(++sequence, new Movie(sequence, "abata", "My first Movie", "action"));
        store.put(++sequence, new Movie(sequence, "demon hunters", "My Second Movie", "animation"));
        store.put(++sequence, new Movie(sequence, "green books", "My Third Movie", "comic"));
    }

    @Override
    public Movie createMovie(Movie movie) {
        if (movie.getId() == null) {
            movie.setId(++sequence);
        }
        store.put(movie.getId(), movie);
        return movie;
    }

    @Override
    public Optional<Movie> findMovieById(int id) {
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public List<Movie> findAllMovies() {
        return new ArrayList<>(store.values());
    }
}
