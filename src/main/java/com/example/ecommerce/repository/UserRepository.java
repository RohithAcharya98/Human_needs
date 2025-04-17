package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    // Method to find user by username
    User findByUsername(String username);

    // Method to find user by email
    User findByEmail(String email);

    // Method to find user by Aadhaar number
    User findByAadhaar(String aadhaar);

    // Check if email exists in the database
    boolean existsByEmail(String email);

    // Check if username exists in the database
    boolean existsByUsername(String username);

}
