package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;  // Assuming you're using Spring Data JPA

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;  // Inject your repository for database access

    public void save(User user) {
        userRepository.save(user);  // Save the user to the database
    }
    public User getUserByEmail(String email) {
    	User user = userRepository.findByEmail(email);
    	if (user == null) {
    	    throw new RuntimeException("User not found");
    	}
    	return user;
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }
}

