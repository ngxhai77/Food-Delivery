package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.Category;
import com.example.deleverysystem.entity.MenuItems;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.CategoryRepository;
import com.example.deleverysystem.repository.MenuItemsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class MenuItemService {

    //Quản lý các thao tác liên quan đến menu món ăn như thêm, sửa, xóa món ăn.
    //Hỗ trợ cho việc xem danh sách menu món ăn của các nhà hàng.

    //Xem danh sách menu món ăn của một nhà hàng
    //Thêm món ăn vào menu của một nhà hàng
    //Sửa thông tin một món ăn trong menu của một nhà hàng
    //Xóa một món ăn khỏi menu của một nhà hàng

    public final MenuItemsRepository menuItemRepository;
    private final CategoryRepository categoryRepository;
    public MenuItemService(MenuItemsRepository menuItemRepository, CategoryRepository categoryRepository) {
        this.menuItemRepository = menuItemRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<MenuItems> findAll(){
        return menuItemRepository.findAll();
    }

    public ResponseEntity create(MenuItems menuItem){
        menuItemRepository.save(menuItem);
        return ResponseEntity.ok("Item created successfully");

    }

    public MenuItems save(MenuItems menuItem){
        return menuItemRepository.save(menuItem);
    }

    public ResponseEntity<MenuItems> findById(Integer id){
         menuItemRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "Item not found: "));
         return ResponseEntity.ok(menuItemRepository.findById(id).get());
    }

    public void deleteById(Integer id){
        menuItemRepository.deleteById(id);
    }

    public MenuItems update(Integer id, MenuItems menuItem){
        MenuItems menuItem1 = menuItemRepository.findById(id).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "Item not found: "));
        menuItem1.setName(menuItem.getName());
        menuItem1.setDescription(menuItem.getDescription());
        menuItem1.setPrice(menuItem.getPrice());
        menuItem1.setImageURL(menuItem.getImageURL());

        return menuItemRepository.save(menuItem1);
    }




    public List<MenuItems> findAllByCategory(String categoryName){
        Category category = categoryRepository.findByCategoryName(categoryName);
        if (category == null) {
            throw new RuntimeException("Category not found: " + categoryName);
        }
        return menuItemRepository.findAllByCategory(category);
    }

    public List<MenuItems> findAllByName(String name){
        return menuItemRepository.findAllByName(name);
    }


    public List<MenuItems> findAllByNameContainingIgnoreCase(String name) {
        return menuItemRepository.findByNameContainingIgnoreCase(name);
    }


//
//    public List<MenuItems> findAllByRestaurantId(Integer restaurantId){
//        return menuItemRepository.findAllByRestaurantId(restaurantId);
//    }
//
//    public List<MenuItems> findAllByCategory(String category){
//        return menuItemRepository.findAllByCategory(category);
//    }
//
//    public List<MenuItems> findAllByPrice(Double price){
//        return menuItemRepository.findAllByPrice(price);
//    }
//
//    public List<MenuItems> findAllByAvailability(Boolean availability){
//        return menuItemRepository.findAllByAvailability(availability);
//    }
//
//
//    public List<MenuItems> findAllByRestaurantIdAndCategory(Integer restaurantId, String category){
//        return menuItemRepository.findAllByRestaurantIdAndCategory(restaurantId, category);
//    }
//
//    public List<MenuItems> findAllByRestaurantIdAndPrice(Integer restaurantId, Double price){
//        return menuItemRepository.findAllByRestaurantIdAndPrice(restaurantId, price);
//    }
//
//    public List<MenuItems> findAllByRestaurantIdAndAvailability(Integer restaurantId, Boolean availability){
//        return menuItemRepository.findAllByRestaurantIdAndAvailability(restaurantId, availability);
//    }
//
//    public List<MenuItems> findAllByCategoryAndPrice(String category, Double price){
//        return menuItemRepository.findAllByCategoryAndPrice(category, price);
//    }
//
//    public List<MenuItems> findAllByCategoryAndAvailability(String category, Boolean availability){
//        return menuItemRepository.findAllByCategoryAndAvailability(category, availability);
//    }
//
//    public List<MenuItems> findAllByPriceAndAvailability(Double price, Boolean availability){
//        return menuItemRepository.findAllByPriceAndAvailability(price, availability);
//    }




}
