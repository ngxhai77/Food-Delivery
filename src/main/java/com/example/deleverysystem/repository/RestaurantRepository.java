package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
    List<Restaurant> findAllByRestaurantAddress(String restaurantAddress);

    List<Restaurant> findAllByRestaurantName(String restaurantName);


}