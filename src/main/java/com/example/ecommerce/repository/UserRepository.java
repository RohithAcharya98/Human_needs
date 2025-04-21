package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
    User findByEmail(String email);
    User findByAadhaar(String aadhaar);
    boolean existsByEmail(String email);
    boolean existsByUsername(String username);

}
