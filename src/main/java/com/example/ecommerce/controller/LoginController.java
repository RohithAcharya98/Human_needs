package com.example.ecommerce.controller;

//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class LoginController {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String showLoginForm() {
        return "login"; // Returns login.html
    }
    

//    @PostMapping("/login")
//    public String processLogin(String email, String password, Model model) {
//        // Get user details from the database (UserDetailsService is used for that)
//        var userDetails = userDetailsService.loadUserByUsername(email);
//
//        // Check if password matches
//        if (passwordEncoder.matches(password, userDetails.getPassword())) {
//            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
//            SecurityContextHolder.getContext().setAuthentication(authentication);
//
//            return "redirect:/home"; // Redirect to the home page after successful login
//        }
//
//        model.addAttribute("error", "Invalid username or password.");
//        return "login"; // If login fails, return to login page with error
//    }
}
