package com.example.ecommerce.model;


import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code; // Coupon code (e.g., ABC123)
    private double discount; // Discount percentage
    private Date startDate; // Start date for coupon validity
    private Date endDate; // End date for coupon validity

    private boolean isActive; // Whether the coupon is active

    // Getters and Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    
    public String getCode() {return code;}
    public void setCode(String code) {this.code = code;}
    
    public double getDiscount() {return discount;}
    public void setDiscount(double discount) {this.discount = discount;}
    
    public Date getStartDate() {return startDate;}
    public void setStartDate(Date startDate) {this.startDate = startDate;}
    
    public Date getEndDate() {return endDate;}
    public void setEndDate(Date endDate) {this.endDate = endDate;}
    
    public boolean isActive() {return isActive;}
    public void setActive(boolean active) {isActive = active;}
}
