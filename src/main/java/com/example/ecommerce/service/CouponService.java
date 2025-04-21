package com.example.ecommerce.service;

import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    // Get all active coupons
    public List<Coupon> getAllActiveCoupons() {
        return couponRepository.findByIsActiveTrue();
    }

    // Validate and apply coupon
    public double applyCoupon(String code, double totalAmount) {
        Coupon coupon = couponRepository.findByCode(code);
        if (coupon != null && coupon.isActive()) {
            double discount = coupon.getDiscount();
            return totalAmount - (totalAmount * (discount / 100));
        }
        return totalAmount; // If coupon is invalid or expired, return original amount
    }

    // Create a new coupon
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    // Deactivate coupon
    public void deactivateCoupon(Long couponId) {
        Coupon coupon = couponRepository.findById(couponId).orElseThrow(() -> new IllegalArgumentException("Coupon not found"));
        coupon.setActive(false);
        couponRepository.save(coupon);
    }
}
