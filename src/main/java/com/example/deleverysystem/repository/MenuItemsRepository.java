package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.MenuItems;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MenuItemsRepository extends JpaRepository<MenuItems, Integer> {
}