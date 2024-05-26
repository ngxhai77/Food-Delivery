package com.example.deleverysystem.service;

import com.example.deleverysystem.dto.OrderItemDTO;
import com.example.deleverysystem.dto.OrderRequestDTO;
import com.example.deleverysystem.dto.OrderResponseDTO;
import com.example.deleverysystem.dto.UserInfoDTO;
import com.example.deleverysystem.entity.*;
import com.example.deleverysystem.exception.ErrorMessage;
import com.example.deleverysystem.mapper.OrderItemMapper;
import com.example.deleverysystem.repository.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

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
    @Autowired
    private OrderItemMapper orderItemMapper;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartService cartService;



    public List<Orders> findAlls(){
          List<Orders> orders = ordersRepository.findAll();
          return orders;
     }

    public List<Orders> findAllByUserid(HttpServletRequest request) throws Exception {
        Integer applicationUserId = tokenService.getIdFromToken(request);
        ApplicationUser applicationUser = userRepository.findById(applicationUserId)
                .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        UserInfo userInfo = applicationUser.getUserInfo();
        return ordersRepository.findOrdersByUserId(userInfo.getUserId());
    }
    public String createOrder(HttpServletRequest request ,OrderRequestDTO orderRequest) throws Exception {
        Orders order = new Orders();
        Integer id = tokenService.getIdFromToken(request );
        ApplicationUser applicationUser = userRepository.findById(id) .orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "User not found: "));
        order.setUserInfo(applicationUser.getUserInfo());
//        order.setRestaurant(restaurantRepository.findById(orderRequest.getRestaurantId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "1 not found: ")));
//        order.setDeliveryPersonnel(deliveryPersonRepository.findById(orderRequest.getDeliveryPersonId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, "2 not found: ")));
        order.setDeliveryAddress(orderRequest.getDeliveryAddress());
        order.setOrderDateTime(LocalDateTime.now());
        order = ordersRepository.save(order);
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemDTO orderItemRequest : orderRequest.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(menuItemRepository.findById(orderItemRequest.getItemId()).orElseThrow(() -> new ErrorMessage(HttpStatus.NOT_FOUND, " 3 not found: ")));
            orderItem.setQuantity(orderItemRequest.getQuantity());
            orderItem.setSubtotal(BigDecimal.valueOf(orderItem.getItem().getPrice() * orderItem.getQuantity()));
            orderItemRepository.save(orderItem);
            totalAmount = totalAmount.add(orderItem.getSubtotal());
        }
        order.setTotalAmount(BigDecimal.valueOf(totalAmount.doubleValue()));

        return "Order created successfully!";
    }



    public List<OrderResponseDTO> findAll(){
        List<Orders> orders = ordersRepository.findAll();
        return orders.stream()
                .map(orderItemMapper::convertToDTO)
                .collect(Collectors.toList());
    }


    @Transactional
    public Orders createOrderFromCart(HttpServletRequest request) throws Exception {
        Integer id  =  cartService.createOrGetCartForUser(request);
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() ->  new ErrorMessage(HttpStatus.NOT_FOUND, " cart not found"));

        Orders order = new Orders();
        order.setUserInfo(cart.getUser());
        order.setOrderDateTime(LocalDateTime.now());
        order.setDeliveryAddress(cart.getUser().getAddress());
        order.setStatus(new Status("Pending")); // Assuming 1 is the ID for a default status like "Pending"

        List<OrderItem> orderItems = new ArrayList<>();
        BigDecimal totalAmount = BigDecimal.ZERO;

        for (CartItem cartItem : cart.getItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(order);
            orderItem.setItem(cartItem.getMenuItem());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setSubtotal(cartItem.getPrice().multiply(BigDecimal.valueOf(cartItem.getQuantity())));

            orderItems.add(orderItem);
            totalAmount = totalAmount.add(orderItem.getSubtotal());
        }

        order.setOrderItems(orderItems);
        order.setTotalAmount(totalAmount);

        order = ordersRepository.save(order);

        // Remove the cart after the order is created
        cartRepository.delete(cart);

        return order;
    }




}