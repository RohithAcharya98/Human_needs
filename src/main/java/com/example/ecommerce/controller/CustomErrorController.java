package com.example.ecommerce.controller;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CustomErrorController implements ErrorController {

    @RequestMapping("/error")
    public String handleError() {
        // You can customize this method to display a custom error page
        return "error"; // This points to the 'error.html' template
    }

    
}
