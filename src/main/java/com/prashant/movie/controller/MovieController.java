package com.prashant.movie.controller;

import com.prashant.movie.entity.Movie;
import com.prashant.movie.service.MovieService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/movies")
public class MovieController {

    private final MovieService service;

    public MovieController(MovieService movieService) {
        this.service = movieService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long movieId) {
        return service.getMovieById(movieId)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        return ResponseEntity.ok(service.createMovie(movie));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(Long id) {
        return service.deleteMovie(id) ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }


    @PutMapping("/{id}")
    public  ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie movie) {
        return service.updateMovie(id, movie)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovies(
            @RequestParam String title,
            @RequestParam Integer releaseYear
    ) {
        List<Movie> movies = service.searchMovie(title, releaseYear);
        if(movies.isEmpty()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(movies);
        }
    }
}
