package com.moviebooking.repositories;

import com.moviebooking.dtos.CinemaDto;
import com.moviebooking.entities.Cinema;
import com.moviebooking.entities.PVR;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CinemaRepository extends JpaRepository<Cinema, Integer> {

    List<Cinema> findByName(String name);

    @Query(value = "select c from Cinema c order by price")
    List<Cinema> getAllCinemaOrderByPrice();

    List<Cinema> findByLocation(String location);

    Cinema findByCinemaId(Integer cinemaId);

    List<Cinema> findByNameAndLocation(String name, String location);
}
