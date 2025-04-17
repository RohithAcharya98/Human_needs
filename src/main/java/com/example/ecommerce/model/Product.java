package com.example.ecommerce.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private int quantity;
    private String name;
    private double price;
    private String imageUrl;
    private String description;
    private String category;
    private int rating;
    private boolean prime;
    private boolean bestSeller;
    private boolean deal;
    
    @Column(name = "previous_price")
    private Double previousPrice;

    private String lastPriceChange;

    public Product(Product p, int quantity) {
        this.id = p.getId();
        this.name = p.getName();
        this.price = p.getPrice();
        this.category = p.getCategory();
        this.quantity = quantity;
    }

    // Getters and Setters
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    
    public String getName() {return name;}
    public void setName(String name) {this.name = name;}
    
    public int getQuantity() {return quantity;}
    public void setQuantity(int quantity) {this.quantity = quantity;}
    
    public double getPrice() {return price;}
    public void setPrice(double price) {this.price = price;}
    
    public String getImageUrl() {return imageUrl;}
    public void setImageUrl(String imageUrl) {this.imageUrl = imageUrl;}
    
    public String getDescription() {return description;}
    public void setDescription(String description) {this.description = description;}
    
    public String getCategory() {return category;}
    public void setCategory(String category) {this.category = category;}
    
    public int getRating() {return rating;}
    public void setRating(int rating) {this.rating = rating;}
    
    public boolean isPrime() {return prime;}
    public void setPrime(boolean prime) {this.prime = prime;}
    
    public boolean isBestSeller() {return bestSeller;}
    public void setBestSeller(boolean bestSeller) {this.bestSeller = bestSeller;}
    
    public boolean isDeal() {return deal;}
    public void setDeal(boolean deal) {this.deal = deal;}

	public String getLastPriceChange() {return lastPriceChange;}

	public void setLastPriceChange(String lastPriceChange) {
		this.lastPriceChange = lastPriceChange;
	}
}
