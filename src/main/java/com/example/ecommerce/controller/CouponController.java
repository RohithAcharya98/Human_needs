package com.example.ecommerce.controller;

import com.example.ecommerce.model.Coupon;
import com.example.ecommerce.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/coupons")
public class CouponController {

    @Autowired
    private CouponService couponService;

    // Get all active coupons
    @GetMapping("/active")
    public List<Coupon> getActiveCoupons() {
        return couponService.getAllActiveCoupons();
    }

    // Apply a coupon to a total amount
    @PostMapping("/apply")
    public double applyCoupon(@RequestParam String code, @RequestParam double totalAmount) {
        return couponService.applyCoupon(code, totalAmount);
    }

    // Create a new coupon
    @PostMapping("/create")
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    // Deactivate a coupon
    @PostMapping("/deactivate/{couponId}")
    public void deactivateCoupon(@PathVariable Long couponId) {
        couponService.deactivateCoupon(couponId);
    }
}
