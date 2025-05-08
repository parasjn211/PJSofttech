package com.monkcommerce.coupons.service;

import com.monkcommerce.coupons.model.Cart;
import com.monkcommerce.coupons.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponService {
    List<Coupon> getAllCoupons();

    Optional<Coupon> getCouponById(Long id);

    Coupon createCoupon(Coupon coupon);

    Coupon updateCoupon(Long id, Coupon updatedCoupon);

    void deleteCoupon(Long id);

    List<Coupon> getApplicableCoupons(Cart cart);

    double applyCouponToCart(Long couponId, Cart cart);
}
