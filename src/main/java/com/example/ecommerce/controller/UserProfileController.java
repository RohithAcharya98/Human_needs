package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;

@Controller
public class UserProfileController {

    @Autowired
    private UserService userService;

    @GetMapping("/profile")
    public String showProfile(Model model, @AuthenticationPrincipal UserDetails userDetails) {
        // Fetch the user details from the database (based on authenticated user)
        User user = userService.getUserByEmail(userDetails.getUsername());
        model.addAttribute("user", user);
        System.out.println("Print Statement :"+user.getEmail());
        model.addAttribute("loggedInUsername", user.getEmail());
        return "profile";  // Returns the profile.html page
    }

    @PostMapping("/profile")
    public String updateProfile(@ModelAttribute User updatedUser, @AuthenticationPrincipal UserDetails userDetails) {
        // Update user details in the database
        User user = userService.getUserByEmail(userDetails.getUsername());
        user.setAddress(updatedUser.getAddress());
        user.setCity(updatedUser.getCity());
        user.setState(updatedUser.getState());
        user.setPincode(updatedUser.getPincode());
        userService.saveUser(user);

        return "redirect:/profile";  // Redirect to the profile page after saving
    }
}
