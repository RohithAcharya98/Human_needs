package com.example.ecommerce.service;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class IntentService {
    private final Map<String, List<String>> intentPatterns = Map.of(
        "order_status", List.of("where is my order", "track order", "order status"),
        "return_policy", List.of("return", "refund", "how to return"),
        "greeting", List.of("hello", "hi", "hey"),
        "fallback", List.of()
    );

    public String detectIntent(String message) {
        String lowerMsg = message.toLowerCase();
        for (Map.Entry<String, List<String>> entry : intentPatterns.entrySet()) {
            for (String pattern : entry.getValue()) {
                if (lowerMsg.contains(pattern)) {
                    return entry.getKey();
                }
            }
        }
        return "fallback";
    }
}

