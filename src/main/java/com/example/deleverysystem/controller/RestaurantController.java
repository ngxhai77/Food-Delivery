package com.example.deleverysystem.controller;


import com.example.deleverysystem.entity.Restaurant;
import com.example.deleverysystem.repository.RestaurantRepository;
import com.example.deleverysystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/restaurant")
public class RestaurantController {

    //Xử lý các yêu cầu liên quan đến nhà hàng như tạo, cập nhật, xóa nhà hàng.
    //Hỗ trợ cho việc tìm kiếm, xem danh sách nhà hàng

    @Autowired
    public RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantService restaurantService;

    @GetMapping("/")
    public String testing(){
        return "RESTAURANT ACCESS LEVEL";
    }
    @PostMapping("/create")
    public Restaurant createRestaurant(@RequestBody Restaurant restaurant) {
        return  restaurantService.save(restaurant);
    }

    @PutMapping("/update")
    public void updateRestaurant() {
        //code
    }

    @DeleteMapping("/deletebyid/{id}")
    public String deleteRestaurant(@PathVariable("id") Integer id) {
        restaurantService.deleteById(id);
        return "Deleted successfully!";
    }

    @GetMapping("/searchbyname")
    public List<Restaurant> searchRestaurantbyName(@RequestBody String restaurantName) {
        return restaurantService.findAllByRestaurantName(restaurantName);

    }

    @GetMapping("/searchbyaddress")
    public List<Restaurant> searchRestaurantbyAddress(@RequestBody String restaurantAddress) {
        return restaurantService.findAllByRestaurantAddress(restaurantAddress);

    }

    @GetMapping("/view/{id}")
    public Restaurant viewRestaurant(@PathVariable Integer id) {
        return restaurantService.findById(id);
    }
    @GetMapping("/viewAll")
    public List<Restaurant> viewAllRestaurant() {
        return restaurantService.findAll();
    }





}
