package com.example.ecommerce.service;

import com.example.ecommerce.model.Product;
import com.example.ecommerce.model.PriceChangeLog;
import com.example.ecommerce.repository.PriceChangeLogRepository;
import com.example.ecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class PriceTrackingService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private PriceChangeLogRepository priceChangeLogRepository;

    public String trackPriceChange(Product product) {
        Optional<Product> dbProductOptional = productRepository.findById(product.getId());

        if (dbProductOptional.isEmpty()) {
            throw new RuntimeException("Product not found with ID: " + product.getId());
        }

        Product dbProduct = dbProductOptional.get();
        Double oldPrice = dbProduct.getPrice();
        Double newPrice = getCurrentPriceFromDatabaseOrAPI(product.getId());

        String priceChange;
        if (newPrice < oldPrice) {
            priceChange = "Price Down: ₹" + newPrice;
        } else if (newPrice > oldPrice) {
            priceChange = "Price Up: ₹" + newPrice;
        } else {
            priceChange = "Price Unchanged: ₹" + newPrice;
        }

        dbProduct.setPrice(newPrice);
        productRepository.save(dbProduct);

        // Log change
        PriceChangeLog log = new PriceChangeLog();
        log.setProduct(dbProduct);
        log.setOldPrice(oldPrice);
        log.setNewPrice(newPrice);
        log.setChangeType(priceChange);
        log.setChangeTimestamp(LocalDateTime.now());
        priceChangeLogRepository.save(log);

        return priceChange + ": ₹" + newPrice;
    }


    private Double getCurrentPriceFromDatabaseOrAPI(Long productId) {
    	 return productRepository.findById(productId)
    		        .map(Product::getPrice)
    		        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productId));
    }
}
