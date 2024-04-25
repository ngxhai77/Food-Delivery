package com.example.deleverysystem.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin("*")
@RequestMapping("/cart")
public class CartController {


    @GetMapping("/")
    public String testing() {
        return "CART ACCESS LEVEL";
    }

    @GetMapping("/addtocart")
    public String addToCart() {
        return "Product added to cart successfully!";
    }

    @GetMapping("/removefromcart")
    public String removeFromCart() {
        return "Product removed from cart successfully!";
    }

    @GetMapping("/viewcart")
    public String viewCart() {
        return "Cart viewed successfully!";
    }

    @GetMapping("/checkout")

    public String checkout() {
        return "Checkout successfully!";
    }

    @GetMapping("/clearcart")
    public String clearCart() {
        return "Cart cleared successfully!";
    }

    @GetMapping("/updatecart")
    public String updateCart() {
        return "Cart updated successfully!";
    }


}
