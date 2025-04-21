package com.example.ecommerce.controller;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.ShippingAddress;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ShippingAddressRepository;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.OrderService;

import jakarta.transaction.Transactional;

@Controller
public class CheckoutController {

    private static final Logger logger = LoggerFactory.getLogger(CheckoutController.class);

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ShippingAddressRepository shippingAddressRepository;

    @Autowired
    private OrderService orderService;

//    private User getUserFromDetails(UserDetails details) {
//        User user = userRepository.findByEmail(details.getUsername());
//        if (user == null) {
//            throw new RuntimeException("User not found: " + details.getUsername());
//        }
//        return user;
//    }

    @GetMapping("/checkout")
    public String showCheckoutPage(Principal principal, Model model) {
        // Fetch the user
        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            model.addAttribute("error", "User not found. Please login again.");
            return "error";
        }

        // Fetch the cart items
        List<CartItem> cartItems = cartItemRepository.findByUser(user);

        // Separate total calculation for cart and saved-for-later items
        double cartTotal = 0;
        double savedForLaterTotal = 0;

        for (CartItem cartItem : cartItems) {
            if (cartItem.isSavedForLater()) {
                savedForLaterTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            } else {
                cartTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            }
        }

        model.addAttribute("user", user);
        // Add the cart total to the model
        model.addAttribute("totalAmount", cartTotal);
        model.addAttribute("savedForLaterTotal", savedForLaterTotal); // If you want to show saved-for-later separately

        return "checkout"; // Return to the checkout page
    }

    @PostMapping("/checkout")
    @Transactional
    public String processCheckout(@RequestParam String paymentMethod, 
                                  Principal principal, 
                                  Model model) {

        // Log the received parameters
        logger.info("Processing checkout for user: " + principal.getName());
        logger.info("Received paymentMethod: " + paymentMethod);

        User user = userRepository.findByEmail(principal.getName());
        if (user == null) {
            model.addAttribute("error", "User not found. Please login again.");
            return "error";
        }

        // Calculate the total amount from the cart
        List<CartItem> cartItems = cartItemRepository.findByUser(user);
        Order order = orderService.placeOrder(user, cartItems, paymentMethod);

        double cartTotal = 0;
        double savedForLaterTotal = 0;

        for (CartItem cartItem : cartItems) {
            if (cartItem.isSavedForLater()) {
                savedForLaterTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            } else {
                cartTotal += cartItem.getProduct().getPrice() * cartItem.getQuantity();
            }
        }

        // Add the cart total to the model
        model.addAttribute("totalAmount", cartTotal);
        model.addAttribute("savedForLaterTotal", savedForLaterTotal); // If you want to show saved-for-later separately
        
        // Add order and user details to the model for the view
        model.addAttribute("order", order);
        model.addAttribute("user", user);

        return "checkout";
    }
    @PostMapping("/checkout/updateUser")
    public String updateUserDetails(@ModelAttribute("user") User updatedUser, Principal principal, RedirectAttributes redirectAttributes) {
        String email = principal.getName(); // fetch logged-in user
        User user = userRepository.findByEmail(email);
        if (user != null) {
            user.setUsername(updatedUser.getUsername());
            user.setPhone(updatedUser.getPhone());
            user.setAddress(updatedUser.getAddress());
            user.setCity(updatedUser.getCity());
            user.setState(updatedUser.getState());
            user.setPincode(updatedUser.getPincode());

            userRepository.save(user);
            redirectAttributes.addFlashAttribute("success", "Shipping info updated successfully!");
        }
        return "redirect:/checkout";
    }
    @PostMapping("/checkout/save-address")
    public String saveShippingAddress( @RequestParam String fullName, @RequestParam String phone,
            						   @RequestParam String address,  @RequestParam String city,
            						   @RequestParam String state,  @RequestParam String pincode, Principal principal) {
        User user = userRepository.findByEmail(principal.getName());

        ShippingAddress shippingAddress = new ShippingAddress();
        shippingAddress.setUsername(fullName);
        shippingAddress.setPhone(phone);
        shippingAddress.setAddress(address);
        shippingAddress.setCity(city);
        shippingAddress.setState(state);
        shippingAddress.setPincode(pincode);
        shippingAddress.setUser(user);

        shippingAddressRepository.save(shippingAddress);

        return "redirect:/checkout"; // or to a confirmation page
    }

}
