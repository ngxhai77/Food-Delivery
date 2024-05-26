package com.example.deleverysystem.service;

import com.example.deleverysystem.entity.Cart;
import com.example.deleverysystem.entity.CartItem;
import com.example.deleverysystem.entity.MenuItems;
import com.example.deleverysystem.entity.UserInfo;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.CartItemRepository;
import com.example.deleverysystem.repository.CartRepository;
import com.example.deleverysystem.repository.MenuItemsRepository;
import com.example.deleverysystem.repository.UserInfoRepository;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Service

public class CartService {

    private final CartRepository cartRepository;


    public CartService(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private MenuItemsRepository menuItemsRepository;

    @Autowired
    private UserInfoRepository userInforepository;
    @Autowired
    private CartRepository cartrepository;

    @Autowired
    private TokenService tokenService;

    @Autowired
    UserInfoService userInfoService;

    public Integer createOrGetCartForUser(HttpServletRequest request) throws Exception {
        Integer id =   userInfoService.getUserIdFromToken(request);
        Cart existingCart = cartRepository.findCartByUserId(id);
       if (existingCart != null) {
            return existingCart.getCartId();
        } else {
            Cart newCart = new Cart();
            UserInfo userInfo = userInforepository.findById(id)
                    .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found"));
            newCart.setUser(userInfo);
            newCart.setCreatedAt(LocalDateTime.now());
            newCart.setUpdatedAt(LocalDateTime.now());
            cartRepository.save(newCart);
            return newCart.getCartId();
        }

//       catch (EmptyResultDataAccessException e) {
//            Cart newCart = new Cart();
//            UserInfo userInfo = userInforepository.findById(id)
//                    .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found"));
//            newCart.setUser(userInfo);
//            newCart.setCreatedAt(LocalDateTime.now());
//            newCart.setUpdatedAt(LocalDateTime.now());
//             cartRepository.save(newCart);
//             return newCart.getCartId();
//        }
    }


    public Cart addToCart(HttpServletRequest request, Integer itemId, int quantity) throws Exception {
        Integer id =  createOrGetCartForUser(request);
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        MenuItems item = menuItemsRepository.findById(itemId).orElseThrow(() -> new RuntimeException("Item not found"));

        CartItem cartItem = new CartItem();
        cartItem.setCart(cart);
        cartItem.setMenuItem(item);
        cartItem.setQuantity(quantity);
        cartItem.setPrice(BigDecimal.valueOf(item.getPrice()).multiply(BigDecimal.valueOf(quantity)));

        cartItemRepository.save(cartItem);

        return cart;
    }

    public Cart removeFromCart(HttpServletRequest request, Integer itemId) throws Exception {
        Integer id =  createOrGetCartForUser(request);
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        CartItem cartItem = cartItemRepository.findCartItemByCartIdAndMenuItemId(id, itemId).orElseThrow(()->new ErrorMessage(HttpStatus.NOT_FOUND, "RESTAURANT not found: "));

        if (cartItem.getQuantity() > 1) {
            cartItem.setQuantity(cartItem.getQuantity() - 1);
            cartItemRepository.save(cartItem);
        } else {
            cartItemRepository.delete(cartItem);
        }

        return cart;
    }

    public List<CartItem> viewCart(HttpServletRequest request) throws Exception {
        Integer id =  createOrGetCartForUser(request);
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        return cart.getItems();
    }

    public void clearCart(HttpServletRequest request) throws Exception{
        Integer id =  createOrGetCartForUser(request);
        Cart cart = cartRepository.findById(id).orElseThrow(() -> new RuntimeException("Cart not found"));
        cartItemRepository.deleteAll(cart.getItems());
    }




}
