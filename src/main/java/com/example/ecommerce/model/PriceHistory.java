package com.example.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class PriceHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long productId;
    private Double oldPrice;
    private Double newPrice;
    private String priceChange;
    
	public Long getProductId() {return productId;}
	public void setProductId(Long productId) {this.productId = productId;}
	
	public Double getOldPrice() {return oldPrice;}
	public void setOldPrice(Double oldPrice) {this.oldPrice = oldPrice;}
	
	public Double getNewPrice() {return newPrice;}
	public void setNewPrice(Double newPrice) {this.newPrice = newPrice;}
	
	public String getPriceChange() {return priceChange;}
	public void setPriceChange(String priceChange) {this.priceChange = priceChange;}

    // Getters and Setters
}
