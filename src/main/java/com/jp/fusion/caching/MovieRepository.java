package com.jp.fusion.caching;

import com.jp.fusion.model.Movie;
import com.jp.fusion.service.RetrievalService;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeMap;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
@Slf4j
@Getter
public class MovieRepository {
    ConcurrentMap<String, Movie> movieConcurrentMap = new ConcurrentHashMap<>();

    @Autowired
    RetrievalService retrievalService;

    @PostConstruct
    public void init() {
        getAllMovies();
    }

    private void getAllMovies() {
        List<Movie> movies = retrievalService.getData(Movie.class);

        movies.forEach(this::setMovieId);
        movieConcurrentMap.putAll(movies
                .parallelStream()
                .collect(Collectors.toConcurrentMap(
                Movie::getId, Function.identity()
        )));
    }

    private void setMovieId(Movie movie) {
        movie.setUUId(UUID.randomUUID().toString());
        movie.setId("Movie" + "#" + movie.getYear() + "#" + UUID.randomUUID().toString());
    }

    /*
        We can have methods here listening tto the database and capturing the document changes
    * */

}
