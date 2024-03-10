package com.jp.fusion.controller;

import com.jp.fusion.model.Movie;
import com.jp.fusion.service.MoviesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Slf4j
public class MoviesController {
    @Autowired
    private MoviesService moviesService;

    @GetMapping("/v1/getMoviesByYear")
    public Flux<Movie> getMoviesByYear(@RequestParam Short year) {
        return moviesService.getMoviesByYear(year);
    }

    @GetMapping("/v1/getMoviesByCastMember")
    public Flux<Movie> getMoviesByCastMember(@RequestParam String cast) {
        return moviesService.getMoviesByCastMember(cast);
    }

    @GetMapping("/v1/getMoviesByGenre")
    public Flux<Movie> getMoviesByGenre(@RequestParam String genre) {
        return moviesService.getMoviesByGenre(genre);
    }

    @GetMapping("/v1/getMoviesByName")
    public Flux<Movie> getMoviesByName(@RequestParam String name) {
        return moviesService.getMoviesByName(name);
    }

}
