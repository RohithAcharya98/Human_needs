package com.example.ecommerce.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class ChatLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String message;
    private String botReply;

    private LocalDateTime timestamp;

	public String getUsername() {return username;}
	public void setUsername(String username) {this.username = username;}

	public String getMessage() {return message;}
	public void setMessage(String message) {this.message = message;}

	public String getBotReply() {return botReply;}
	public void setBotReply(String botReply) {this.botReply = botReply;}

	public LocalDateTime getTimestamp() {return timestamp;}
	public void setTimestamp(LocalDateTime timestamp) {this.timestamp = timestamp;}
}
