package com.jp.fusion.service;

import com.jp.fusion.caching.MovieRepository;
import com.jp.fusion.model.Movie;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

import java.time.Year;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
@Slf4j
public class MoviesService {
    @Autowired
    MovieRepository movieRepository;

    public Flux<Movie> getMoviesByYear(short year) {
        List<Movie> movies = movieRepository
                .getMovieConcurrentMap()
                .values()
                .parallelStream()
                .filter(m -> m.getYear() == year)
                .toList();
        return Flux.fromIterable(movies);
    }

    public Flux<Movie> getMoviesByName(String title) {
        List<Movie> movies = movieRepository
                .getMovieConcurrentMap()
                .values()
                .parallelStream()
                .filter(m -> title.equals(m.getTitle()))
                .toList();
        return Flux.fromIterable(movies);
    }

    public Flux<Movie> getMoviesByCastMember(String cast) {
        List<Movie> movies = movieRepository
                .getMovieConcurrentMap()
                .values()
                .parallelStream()
                .filter(m -> m.getCast() != null && m.getCast().parallelStream().anyMatch(c -> c.contains(cast)))
                .toList();
        return Flux.fromIterable(movies);
    }

    public Flux<Movie> getMoviesByGenre(String genre) {
        List<Movie> movies = movieRepository
                .getMovieConcurrentMap()
                .values()
                .parallelStream()
                .filter(m -> m.getGenres() != null && m.getGenres().parallelStream().anyMatch(genre::equals))
                .toList();
        return Flux.fromIterable(movies);
    }
}
