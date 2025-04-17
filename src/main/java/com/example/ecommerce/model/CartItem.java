package com.example.ecommerce.model;

import jakarta.persistence.*;

@Entity
@Table(name = "cart_item", uniqueConstraints = {
	    @UniqueConstraint(columnNames = {"user_id", "product_id"})
	})
public class CartItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    private Product product;
    
    
    @Column(name = "saved_for_later")
    private boolean savedForLater = false;
    
    private int quantity;

    public CartItem() {}

    public CartItem(User user, Product product, int quantity) {
        this.user = user;
        this.product = product;
        this.quantity = quantity;
    }

    public void setSavedForLater(boolean savedForLater) {this.savedForLater=savedForLater;}
    
    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public Product getProduct() { return product; }
    public void setProduct(Product product) { this.product = product; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
} 