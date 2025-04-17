package com.example.ecommerce.service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;  // Assuming you're using Spring Data JPA
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Inject your repository for database access

    public void save(User user) {
        userRepository.save(user);  // Save the user to the database
    }
}

