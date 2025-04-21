package com.example.ecommerce.repository;

import com.example.ecommerce.model.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    // Find active coupons
    List<Coupon> findByIsActiveTrue();

    // Find a coupon by its code
    Coupon findByCode(String code);
}
