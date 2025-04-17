package com.example.ecommerce.controller;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {

    private final CartService cartService;
    private final ProductService productService;

    public HomeController(CartService cartService, ProductService productService) {
        this.cartService = cartService;
        this.productService = productService;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Product> productList = productService.getAllProducts();
        model.addAttribute("products", productList);
        model.addAttribute("categories", getAllCategories(productList));
        return "index";
    }

    @PostMapping("/add-to-cart")
    public String addToCart(@RequestParam Long id, @RequestParam int quantity, Principal principal) {
        Product product = productService.getProductById(id);
        if (product != null && principal != null) {
            cartService.addProduct(product, quantity, principal.getName());
        }
        return "redirect:/";
    }


//    @GetMapping("/cart")
//    public String cart(Model model, Principal principal) {
//        List<CartItem> cartItems = cartService.getCart(principal.getName());
//        double total = cartItems.stream()
//                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
//                .sum();
//
//        model.addAttribute("cart", cartItems);
//        model.addAttribute("total", total);
//        return "cart";
//    }

    @GetMapping("/search")
    public String searchProducts(@RequestParam String keyword, Model model) {
        List<Product> results = productService.searchByName(keyword);
        model.addAttribute("products", results);
        model.addAttribute("categories", getAllCategories(results));
        return "shop";
    }

    @GetMapping("/filter")
    public String filterByCategory(@RequestParam String category, Model model) {
        List<Product> results = category.isEmpty()
                ? productService.getAllProducts()
                : productService.getByCategory(category);
        model.addAttribute("products", results);
        model.addAttribute("categories", getAllCategories(results));
        return "shop";
    }

    @PostMapping("/checkout")
    public String checkout(Principal principal) {
        String userEmail = principal.getName(); // Get logged-in user's email
        cartService.clearCart(userEmail);       // Clear cart for that user
        return "redirect:/cart";
    }

    private List<String> getAllCategories(List<Product> products) {
        return products.stream()
                .map(Product::getCategory)
                .distinct()
                .toList();
    }
    


}
