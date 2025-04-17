package com.example.ecommerce.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import com.example.ecommerce.service.EmailService;

import jakarta.validation.Valid;

@Controller
public class SignupController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private EmailService emailService;

    @GetMapping("/signup")
    public String showSignupForm(Model model) {
        model.addAttribute("user", new User());
        return "signup";
    }

    @PostMapping("/signup")
    public String processSignup(
            @Valid @ModelAttribute("user") User user,
            BindingResult bindingResult,
            @RequestParam("confirmPassword") String confirmPassword,
            Model model,
            RedirectAttributes redirectAttributes
    ) {
        // Handle validation errors
        if (bindingResult.hasErrors()) {
            return "signup";
        }

        // Check if email already exists
        if (userRepository.findByEmail(user.getEmail()) != null) {
            model.addAttribute("error", "Email is already registered");
            return "signup";
        }

        // Check if Aadhaar is already registered
        if (userRepository.findByAadhaar(user.getAadhaar()) != null) {
            model.addAttribute("error", "Aadhaar number is already registered");
            return "signup";
        }

        // Password match check
        if (!user.getPassword().equals(confirmPassword)) {
            model.addAttribute("error", "Passwords do not match");
            return "signup";
        }

        // encode password
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // Save the user
        userRepository.save(user);
        emailService.sendWelcomeEmail(user.getEmail(), user.getUsername());
        //System.out.println("User saved: " + user);

        // Redirect with success
        redirectAttributes.addFlashAttribute("success", "Signup successful! Please log in.");
        return "redirect:/login";
    }
}
