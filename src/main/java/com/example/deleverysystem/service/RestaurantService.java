package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.Restaurant;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.RestaurantRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    public final RestaurantRepository restaurantRepository;

    public RestaurantService(RestaurantRepository restaurantRepository) {
        this.restaurantRepository = restaurantRepository;
    }

    public List<Restaurant> findAll(){
        return restaurantRepository.findAll();
    }

    public Restaurant create(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public Restaurant save(Restaurant restaurant){
        return restaurantRepository.save(restaurant);
    }

    public Restaurant findById(Integer id){
        return restaurantRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "RESTAURANT not found: "));
    }

    public void deleteById(Integer id){
        restaurantRepository.deleteById(id);
    }

    public Restaurant update(Integer id, Restaurant restaurant){
        Restaurant restaurant1 = restaurantRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "RESTAURANT not found: "));
        restaurant1.setRestaurantName(restaurant.getRestaurantName());
        restaurant1.setRestaurantAddress(restaurant.getRestaurantAddress());
        restaurant1.setRestaurantPhone(restaurant.getRestaurantPhone());
    //    restaurant1.setRating(restaurant.getRating());  //add rating to the restaurant if possible
        return restaurantRepository.save(restaurant1);
    }

    public List<Restaurant> findAllByRestaurantName(String restaurantName){
        return restaurantRepository.findAllByRestaurantName(restaurantName);
    }

    public List<Restaurant> findAllByRestaurantAddress(String restaurantAddress){
        return restaurantRepository.findAllByRestaurantAddress(restaurantAddress);
    }

//    public List<Restaurant> findAllByRating(Double rating){
//        return restaurantRepository.findAllByRating(rating);
//    }







}
