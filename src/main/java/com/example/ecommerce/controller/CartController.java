package com.example.ecommerce.controller;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    private User getUserFromDetails(UserDetails details) {
        User user = userRepository.findByEmail(details.getUsername());
        if (user == null) {
            throw new RuntimeException("User not found: " + details.getUsername());
        }
        return user;
    }

    @GetMapping
    public String viewCart(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getUserFromDetails(userDetails);
        List<CartItem> activeCart = cartItemRepository.findByUserAndSavedForLaterFalse(user);
        List<CartItem> savedItems = cartItemRepository.findByUserAndSavedForLaterTrue(user);

        double total = activeCart.stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
        BigDecimal totalAmount = new BigDecimal(total).setScale(2, RoundingMode.HALF_UP);

        //model.addAttribute("cart", activeCart);
        model.addAttribute("savedItems", savedItems);
        model.addAttribute("totalAmount", totalAmount);
        return "cart";
    }

    @PostMapping("/increase")
    public String increase(@RequestParam Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        cartService.increaseQuantity(getUserFromDetails(userDetails), productId);
        return "redirect:/cart";
    }

    @PostMapping("/decrease")
    public String decrease(@RequestParam Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        cartService.decreaseQuantity(getUserFromDetails(userDetails), productId);
        return "redirect:/cart";
    }

    @PostMapping("/remove")
    public String remove(@RequestParam Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        cartService.removeFromCart(getUserFromDetails(userDetails), productId);
        return "redirect:/cart";
    }

    @PostMapping("/saveForLater")
    public String saveForLater(@RequestParam Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getUserFromDetails(userDetails);
        Optional<CartItem> itemOpt = cartItemRepository.findByUserAndProductId(user, productId);
        if (itemOpt.isPresent()) {
            CartItem item = itemOpt.get();
            item.setSavedForLater(true);
            cartItemRepository.save(item);
        } else {
            // Handle the case where no item was found
        }

        return "redirect:/cart";
    }

    @PostMapping("/moveToCart")
    public String moveToCart(@RequestParam Long productId, @AuthenticationPrincipal UserDetails userDetails) {
        User user = getUserFromDetails(userDetails);
        Optional<CartItem> itemOpt = cartItemRepository.findByUserAndProductId(user, productId);
        if (itemOpt.isPresent()) {
            CartItem item = itemOpt.get();
            item.setSavedForLater(false);
            cartItemRepository.save(item);
        } else {
            // Handle the case where no item was found
        }

        return "redirect:/cart";
    }
}
