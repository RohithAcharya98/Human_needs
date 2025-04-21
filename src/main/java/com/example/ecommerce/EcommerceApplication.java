package com.example.ecommerce;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

//import com.example.ecommerce.model.User;
//import com.example.ecommerce.repository.UserRepository;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.ecommerce.repository")
public class EcommerceApplication { //implements CommandLineRunner{
	
	@Autowired
    //private UserRepository userRepository;
	
    public static void main(String[] args) {
        SpringApplication.run(EcommerceApplication.class, args);
    }
    
//    @Override
//    public void run(String... args) throws Exception {
//        // Fetch user based on email (for testing purposes, replace with an actual email)
//        String email = "rohithmulampaka2998@gmail.com";  // Use a test user email
//
//        User user = userRepository.findByEmail(email);
//
//        if (user != null) {
//            System.out.println("User found: " + user);
//            // Print individual fields
//            System.out.println("Username: " + user.getUsername());
//            System.out.println("Address: " + user.getAddress());
//            System.out.println("City: " + user.getCity());
//            System.out.println("Pincode: " + user.getPincode());
//            // You can print all necessary fields here
//        } else {
//            System.out.println("User not found!");
//        }
//    }
}
