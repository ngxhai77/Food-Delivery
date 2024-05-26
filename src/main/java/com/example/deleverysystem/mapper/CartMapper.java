package com.example.deleverysystem.mapper;

import com.example.deleverysystem.dto.CartRequest;
import com.example.deleverysystem.dto.CartResponse;
import com.example.deleverysystem.entity.CartItem;
import com.example.deleverysystem.entity.MenuItems;

import java.math.BigDecimal;

public class CartMapper {

    public static CartResponse toCartResponse(CartItem cartItem) {
        CartResponse cartResponse = new CartResponse();
        cartResponse.setMenuItem(cartItem.getMenuItem());
        cartResponse.setQuantity(cartItem.getQuantity());
        cartResponse.setPrice(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));
        return cartResponse;
    }


}
