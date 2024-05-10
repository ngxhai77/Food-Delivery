package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.Category;
import com.example.deleverysystem.entity.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer> {


    List<MenuItems> findAllByCategory(Category category);

    List<MenuItems> findAllByName(String name);

    List<MenuItems> findByNameContainingIgnoreCase(String name);
}