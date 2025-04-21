package com.example.ecommerce.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.UserRepository;

@ControllerAdvice
public class GlobalController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private User getUserFromDetails(UserDetails details) {
        return userRepository.findByEmail(details.getUsername());
    }

    @ModelAttribute("cart")
    public List<CartItem> globalCart(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return List.of(); // unauthenticated users
        User user = getUserFromDetails(userDetails);
        return cartItemRepository.findByUserAndSavedForLaterFalse(user);
    }

    @ModelAttribute("cartCount")
    public int globalCartCount(@AuthenticationPrincipal UserDetails userDetails) {
        if (userDetails == null) return 0;
        User user = getUserFromDetails(userDetails);
        return cartItemRepository.countByUserAndSavedForLaterFalse(user);
    }
    @GetMapping("/order/confirmation")
    public String showOrderConfirmationPage() {
        return "order_confirmation"; // Make sure you have this HTML file
    }

}
