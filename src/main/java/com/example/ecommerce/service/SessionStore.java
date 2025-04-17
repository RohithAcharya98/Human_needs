package com.example.ecommerce.service;

import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.Map;

@Service
public class SessionStore {
    private final Map<String, String> sessionMap = new HashMap<>();

    public void rememberLastIntent(String sessionId, String intent) {
        sessionMap.put(sessionId, intent);
    }

    public String getLastIntent(String sessionId) {
        return sessionMap.getOrDefault(sessionId, "none");
    }
}
