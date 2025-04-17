package com.example.ecommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.ecommerce.model.ChatRequest;
import com.example.ecommerce.model.Product;
import com.example.ecommerce.repository.ProductRepository;
import com.example.ecommerce.service.IntentService;
import com.example.ecommerce.service.PriceTrackingService;
import com.example.ecommerce.service.ResponseService;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

	@Autowired
	private IntentService intentService;

	@Autowired
	private ResponseService responseService;

	@Autowired
	private PriceTrackingService priceTrackingService;

	@Autowired
	private ProductRepository productRepository;

	private List<String> chatHistory = new ArrayList<>();

	@PostMapping
	public ResponseEntity<String> chat(@RequestBody ChatRequest chatRequest) {
	    try {
	        if (chatRequest.getMessage() == null || chatRequest.getSessionId() == null) {
	            return ResponseEntity.badRequest().body("Invalid request: message and sessionId are required.");
	        }

	        String message = chatRequest.getMessage().trim();
	        String sessionId = chatRequest.getSessionId().trim();

	        if (message.isEmpty() || sessionId.isEmpty()) {
	            return ResponseEntity.badRequest().body("Message or sessionId cannot be empty.");
	        }

	        chatHistory.add("User (" + sessionId + "): " + message);

	        if (message.toLowerCase().contains("price") || message.toLowerCase().contains("cost")) {
	            // Extract product name from user message (you can improve this logic)
	            String productName = extractProductName(message);  // Implement this method

	            if (productName == null) {
	                return ResponseEntity.badRequest().body("Please specify a product name to check the price.");
	            }

	            Optional<Product> productOpt = productRepository.findByNameIgnoreCase(productName); // Make sure this method exists

	            System.out.println("Rohith "+productOpt);
	            
	            if (productOpt.isPresent()) {
	                Product product = productOpt.get();
	                String priceChangeMessage = priceTrackingService.trackPriceChange(product);
	                chatHistory.add("Bot: " + priceChangeMessage);
	                return ResponseEntity.ok(priceChangeMessage);
	            } else {
	                String msg = "Sorry, I couldn't find a product named \"" + productName + "\".";
	                chatHistory.add("Bot: " + msg);
	                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
	            }
	        }

	        String intent = intentService.detectIntent(message);
	        String reply = responseService.generateResponse(intent, message, sessionId);
	        chatHistory.add("Bot: " + reply);
	        return ResponseEntity.ok(reply);

	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("Server Error: " + e.getMessage());
	    }
	}
	// You can place this just before the closing curly brace of the class
	private String extractProductName(String message) {
	    List<Product> products = productRepository.findAll();  // Get all products
	    String userMessage = message.toLowerCase();

	    String bestMatch = null;
	    int highestScore = 0;

	    for (Product product : products) {
	        String productName = product.getName().toLowerCase();

	        int score = similarityScore(userMessage, productName);
	        if (score > highestScore && score > 60) { // Adjust threshold if needed
	            highestScore = score;
	            bestMatch = product.getName();
	        }
	    }

	    return bestMatch;
	}

	private int similarityScore(String a, String b) {
	    // Very simple similarity scoring: count common words
	    String[] wordsA = a.split(" ");
	    String[] wordsB = b.split(" ");

	    int matches = 0;
	    for (String wordA : wordsA) {
	        for (String wordB : wordsB) {
	            if (wordA.equalsIgnoreCase(wordB)) {
	                matches++;
	            }
	        }
	    }

	    return (int) (((double) matches / wordsB.length) * 100);
	}




}