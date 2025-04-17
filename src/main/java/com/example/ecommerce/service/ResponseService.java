package com.example.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ResponseService {

    @Autowired
    private SessionStore sessionStore;

    public String generateResponse(String intent, String userMsg, String sessionId) {
        sessionStore.rememberLastIntent(sessionId, intent);

        return switch (intent) {
            case "greeting" -> "Hello! How can I assist you today?";
            case "order_status" -> "Please provide your order ID so I can check the status.";
            case "return_policy" -> "Our return policy allows returns within 7 days of delivery.";
            case "fallback" -> "I'm not sure I understand that. Could you please rephrase?";
            default -> "Sorry, something went wrong.";
        };
    }
}