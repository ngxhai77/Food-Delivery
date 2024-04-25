package com.example.deleverysystem.controller;

import com.example.deleverysystem.entity.MenuItems;
import com.example.deleverysystem.service.MenuItemService;
import com.example.deleverysystem.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/menu")
@CrossOrigin("*")
public class MenuItemController {
    //Quản lý các thao tác liên quan đến menu món ăn như thêm, sửa, xóa món ăn.
    //Hỗ trợ cho việc xem danh sách menu món ăn của các nhà hàng.

    //Xem danh sách menu món ăn của một nhà hàng
    //Thêm món ăn vào menu của một nhà hàng
    //Sửa thông tin một món ăn trong menu của một nhà hàng
    //Xóa một món ăn khỏi menu của một nhà hàng
    @Autowired
    public MenuItemService menuItemService;


    @GetMapping("/")
    public String testing(){
        return "MENU ITEM ACCESS LEVEL";
    }

    @GetMapping("/create")
    public MenuItems createItem(@RequestBody MenuItems items) {
        return menuItemService.create(items);

    }

    @GetMapping("/update")
    public MenuItems updateItem(@RequestBody MenuItems items) {
        return menuItemService.save(items);
    }

    @GetMapping("/delete/{id}")
    public String deleteItem(@PathVariable Integer id) {
        menuItemService.deleteById(id);
        return "Deleted successfully!";
    }

    @GetMapping("/viewall")
    public Iterable<MenuItems> viewAllItems() {
        return menuItemService.findAll();
    }

    @GetMapping("/view/{id}")
    public MenuItems viewItem(@PathVariable Integer id) {
        return menuItemService.findById(id);
    }

    @GetMapping("/update/{id}")
    public MenuItems updateItem(@PathVariable Integer id, @RequestBody MenuItems items) {
        return menuItemService.update(id, items);
    }











}