package com.example.ecommerce.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.OrderItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderItemRepository;
import com.example.ecommerce.repository.OrderRepository;

import jakarta.transaction.Transactional;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;
    
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    public Order placeOrder(User user, List<CartItem> cartItems, String paymentMethod) {
        Order order = new Order();
        order.setUser(user);
        order.setOrderDate(LocalDateTime.now());
        order.setPaymentMethod(paymentMethod);

        List<OrderItem> items = new ArrayList<>();
        double total = 0;

        for (CartItem cartItem : cartItems) {
            OrderItem item = new OrderItem();
            item.setOrder(order); // IMPORTANT: Link each item to this order
            item.setProduct(cartItem.getProduct());
            item.setQuantity(cartItem.getQuantity());
            item.setPrice(cartItem.getProduct().getPrice() * cartItem.getQuantity());
            total += item.getPrice();
            items.add(item);
        }

        order.setTotalAmount(total);
        order.setItems(items); // Set before saving to enable cascade

        try {
            Order savedOrder = orderRepository.save(order); // This will also save OrderItems
            System.out.println("Order placed with ID: " + savedOrder.getId());
            return savedOrder;
        } catch (Exception e) {
            System.out.println("Error saving order: " + e.getMessage());
            return null;
        }
    }



}

