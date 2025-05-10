package com.prashant.movie.service;

import com.prashant.movie.entity.Movie;
import com.prashant.movie.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository repository;

    public MovieService(MovieRepository movieRepository) {
        this.repository = movieRepository;
    }

    public Optional<Movie> getMovieById(Long id) {
        return repository.findById(id);
    }

    public Movie createMovie(Movie movie) {
        return repository.save(movie);
    }

    public Optional<Movie> updateMovie(Long id, Movie updatedMovie) {
        return repository.findById(id).map( movie -> {
                    movie.setTitle(updatedMovie.getTitle());
                    movie.setReleaseYear(updatedMovie.getReleaseYear());
                    return repository.save(movie);
                }
        );
    }

    public boolean deleteMovie(Long id) {
       return repository.findById(id)
                .map(movie -> {
                    repository.delete(movie);
                    return true;
                })
                .orElse(false);
    }

    public List<Movie> searchMovie(String title, Integer releaseYear) {
        return repository.findByTitleContainingIgnoreCaseAndReleaseYear(title, releaseYear);
    }
}
