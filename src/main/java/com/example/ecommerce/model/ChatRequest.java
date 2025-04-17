package com.example.ecommerce.model;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ChatRequest {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
    private String message;
    private String sessionId;
    private String sender;

    public ChatRequest() {}

    public ChatRequest(String message, String sessionId) {
        this.message = message;
        this.sessionId = sessionId;
    }

    public String getMessage() {return message;}

    public void setMessage(String message) {this.message = message;}
    public String getSessionId() {return sessionId;}
    public void setSessionId(String sessionId) { this.sessionId = sessionId;}

	public String getSender() {return sender;}
	public void setSender(String sender) {this.sender = sender;}
}
