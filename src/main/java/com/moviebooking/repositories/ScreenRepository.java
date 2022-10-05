package com.moviebooking.repositories;

import com.moviebooking.entities.Screen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ScreenRepository extends JpaRepository<Screen, Integer> {

    List<Screen> findByType(String type);

    Screen findByScreenId(Integer screenId);
}
