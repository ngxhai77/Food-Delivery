package com.example.deleverysystem.controller;

import com.example.deleverysystem.dto.CartRequest;
import com.example.deleverysystem.dto.CartResponse;
import com.example.deleverysystem.entity.CartItem;
import com.example.deleverysystem.mapper.CartMapper;
import com.example.deleverysystem.service.CartService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class CartController {

    @Autowired
    public CartService cartService;


    @GetMapping("/")
    public String testing() {
        return "CART ACCESS LEVEL";
    }

    @PostMapping("/addtocart")
    public ResponseEntity<?> addToCart(HttpServletRequest request, @RequestBody CartRequest cartRequest) throws Exception {
        cartService.addToCart(request, cartRequest.getItemId(), cartRequest.getQuantity());
        return ResponseEntity.ok("Product added to cart successfully!");
    }

    @DeleteMapping("/removefromcart")
    public ResponseEntity<?> removeFromCart(HttpServletRequest request) {
        return ResponseEntity.ok("Product removed from cart successfully!");
    }

    @GetMapping("/viewcart")
    public ResponseEntity<List<CartResponse>> viewCart(HttpServletRequest request) throws Exception {
        List<CartItem> cartItems = cartService.viewCart(request);
        List<CartResponse> cartResponses = cartItems.stream()
                .map(CartMapper::toCartResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(cartResponses);
    }
    @PostMapping("/checkout")

    public String checkout() {
        return "Checkout successfully!";
    }

    @DeleteMapping("/clearcart")
    public   ResponseEntity<?> clearCart(HttpServletRequest request) throws Exception {
        cartService.clearCart(request);
        return ResponseEntity.ok("Cart cleared successfully!");
    }

    @PutMapping("/updatecart")
    public String updateCart() {
        return "Cart updated successfully!";
    }


}
