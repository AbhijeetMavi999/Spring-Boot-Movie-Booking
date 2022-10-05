package com.moviebooking.repositories;

import com.moviebooking.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    User findByEmail(String email);

    User findByUserName(String userName);

    User findByUserId(Integer userId);

    void deleteByUserId(Integer userId);
}
