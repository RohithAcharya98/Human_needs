package com.example.ecommerce.repository;

import com.example.ecommerce.model.PriceChangeLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PriceChangeLogRepository extends JpaRepository<PriceChangeLog, Long> {

}
