package com.example.ecommerce.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartItemRepository;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.repository.UserRepository;

@Service
public class CartService {

    @Autowired
    private CartItemRepository cartItemRepository;
    
    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void addProduct(Product product, int quantity, String username) {
        User user = userRepository.findByEmail(username);
        CartItem item = new CartItem(user, product, quantity);
        cartItemRepository.save(item);
    }

    public List<CartItem> getCart(String username) {
        User user = userRepository.findByEmail(username);
        return cartItemRepository.findByUser(user);
    }

    public void clearCart(String username) {
        User user = userRepository.findByEmail(username);
        cartItemRepository.deleteByUser(user);
    }
    public List<CartItem> getCartItems(User user) {
        return cartItemRepository.findByUserAndSavedForLaterFalse(user);
    }

    public double getTotal(User user) {
        return getCartItems(user).stream()
                .mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity())
                .sum();
    }

    public void addToCart(User user, Long productId) {
        Product product = productRepository.findById(productId).orElseThrow();
        CartItem item = cartItemRepository.findByUserAndProduct(user, product)
                .orElse(new CartItem());
        item.setUser(user);
        item.setProduct(product);
        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

    public void increaseQuantity(User user, Long productId) {
        CartItem item = cartItemRepository.findByUserAndProduct(user, productRepository.findById(productId).orElseThrow()).orElseThrow();
        item.setQuantity(item.getQuantity() + 1);
        cartItemRepository.save(item);
    }

    public void decreaseQuantity(User user, Long productId) {
        CartItem item = cartItemRepository.findByUserAndProduct(user, productRepository.findById(productId).orElseThrow()).orElseThrow();
        if (item.getQuantity() > 1) {
            item.setQuantity(item.getQuantity() - 1);
            cartItemRepository.save(item);
        } else {
            cartItemRepository.delete(item);
        }
    }

    public void removeFromCart(User user, Long productId) {
        CartItem item = cartItemRepository.findByUserAndProduct(user, productRepository.findById(productId).orElseThrow()).orElseThrow();
        cartItemRepository.delete(item);
    }

    public void saveForLater(User user, Long productId) {
        CartItem item = cartItemRepository.findByUserAndProduct(user, productRepository.findById(productId).orElseThrow()).orElseThrow();
        item.setSavedForLater(true);
        cartItemRepository.save(item);
    }
}