package com.example.deleverysystem.controller;

import com.example.deleverysystem.entity.Category;
import com.example.deleverysystem.entity.MenuItems;
import com.example.deleverysystem.service.CategotyService;
import com.example.deleverysystem.service.MenuItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @Autowired
    public CategotyService categotyService;


    @GetMapping("/")
    public String testing(){
        return "MENU ITEM ACCESS LEVEL";
    }

    @GetMapping("/create")
    public ResponseEntity createItem(@RequestBody MenuItems items) {
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
        return menuItemService.findById(id).getBody();
    }

    @GetMapping("/viewbycategory/{category}")
    public Iterable<MenuItems> viewItemByCategory(@PathVariable String category) {
        return menuItemService.findAllByCategory(category);
    }


    @GetMapping("/update/{id}")
    public MenuItems updateItem(@PathVariable Integer id, @RequestBody MenuItems items) {
        return menuItemService.update(id, items);
    }

    @GetMapping("/food")
    public ResponseEntity<List<MenuItems>> viewFood() {
         List<MenuItems> menuItems = menuItemService.findAll();
         return ResponseEntity.ok().body(menuItems);
    }

    @GetMapping("/category")
    public ResponseEntity<List<Category>> viewCategory() {
        List<Category> categories = categotyService.findAll();
        return ResponseEntity.ok().body(categories);
    }


    @GetMapping("/search")
    public ResponseEntity<List<MenuItems>> searchItem(@RequestParam String name) {
        List<MenuItems> menuItems = menuItemService.findAllByNameContainingIgnoreCase(name);
        return ResponseEntity.ok().body(menuItems);
    }












}