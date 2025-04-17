package com.example.ecommerce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class AuthController {


    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        // Manually invalidate session
        request.getSession().invalidate();
        return "redirect:/login?logout=true";
    }


}
