package com.example.ecommerce.repository;

import com.example.ecommerce.model.ChatLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChatLogRepository extends JpaRepository<ChatLog, Long> {}