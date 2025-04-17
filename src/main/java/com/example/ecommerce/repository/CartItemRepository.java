package com.example.ecommerce.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.ecommerce.model.CartItem;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.User;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    List<CartItem> findByUser(User user);
    void deleteByUser(User user);
    void deleteByUserEmail(String userEmail);
    List<CartItem> findByUserAndSavedForLaterFalse(User user);
    List<CartItem> findByUserAndSavedForLaterTrue(User user);
    Optional<CartItem> findByUserAndProduct(User user, Product product);
    Optional<CartItem> findByUserAndProductId(User user, Long productId);
    int countByUserAndSavedForLaterFalse(User user);
}