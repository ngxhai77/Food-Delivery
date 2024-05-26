package com.example.deleverysystem.repository;

import com.example.deleverysystem.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Integer> {
    @Query("SELECT ci FROM CartItem ci WHERE ci.cart.id = :cartId AND ci.menuItem.id = :menuItemId")
    Optional<CartItem> findCartItemByCartIdAndMenuItemId(@Param("cartId") Integer cartId, @Param("menuItemId") Integer menuItemId);
}