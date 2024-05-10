package com.example.deleverysystem.service;

import com.example.deleverysystem.dto.OrderItemDTO;
import com.example.deleverysystem.dto.OrderRequestDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.*;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@Service
public class OrderService {

    public final OrdersRepository ordersRepository;

    public OrderService(OrdersRepository ordersRepository) {
        this.ordersRepository = ordersRepository;
    }

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private MenuItemsRepository menuItemRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;
    @Autowired
    private DeliveryPersonnelRepository deliveryPersonRepository;
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;



    public List<Orders> findAll(){
        return ordersRepository.findAll();

    }

    public List<Orders> findAllByUserid(HttpServletRequest request) throws Exception {
        Integer applicationUserId = tokenService.getIdFromToken(request);
        ApplicationUser applicationUser = userRepository.findById(applicationUserId)
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        UserInfo userInfo = applicationUser.getUserInfo();
        return ordersRepository.findAllByUserInfo_UserId(userInfo.getUserId());
    }
    public String createOrder(HttpServletRequest request ,OrderRequestDTO orderRequest) throws Exception {
        Orders order = new Orders();
        Integer id = tokenService.getIdFromToken(request );
        ApplicationUser applicationUser = userRepository.findById(id) .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        order.setUserInfo(applicationUser.getUserInfo());
        order.setRestaurant(restaurantRepository.findById(orderRequest.getRestaurantId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "1 not found: ")));
        order.setDeliveryPersonnel(deliveryPersonRepository.findById(orderRequest.getDeliveryPersonId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "2 not found: ")));
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setOrderDateTime(LocalDateTime.now());
        order = ordersRepository.save(order);

        for (OrderItemDTO orderItemRequest : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(menuItemRepository.findById(orderItemRequest.getItemId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, " 3 not found: ")));
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setSubtotal(orderItem.getItem().getPrice() * orderItem.getQuantity());
            orderItemRepository.save(orderItem);
        }

        return "Order created successfully!";
    }



}