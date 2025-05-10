package com.prashant.movie.repository;

import com.prashant.movie.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Long> {

    List<Movie> findByTitleContainingIgnoreCaseAndReleaseYear(String title, int releaseYear);
}
