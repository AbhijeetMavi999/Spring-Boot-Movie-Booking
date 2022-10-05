package com.moviebooking.repositories;

import com.moviebooking.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieRepository extends JpaRepository<Movie, Integer> {

    Movie findByTitle(String title);

    Movie findByMovieId(Integer movieId);
}
