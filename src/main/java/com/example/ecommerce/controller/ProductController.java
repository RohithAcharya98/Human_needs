package com.example.ecommerce.controller;

import com.example.ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    @GetMapping("/products")
    public String viewProducts(Model model) {
        model.addAttribute("products", productService.getAllProducts());
        return "products";  // Thymeleaf template (products.html)
    }
    // You can add DB-related endpoints here later
}
