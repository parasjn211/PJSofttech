package com.monkcommerce.coupons.controller;

import com.monkcommerce.coupons.model.Cart;
import com.monkcommerce.coupons.model.Coupon;
import com.monkcommerce.coupons.service.CouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/coupons")
public class CouponController {
    @Autowired
    private CouponService couponService;

    // GET all coupons
    @GetMapping
    public List<Coupon> getAllCoupons() {
        return couponService.getAllCoupons();
    }

    // GET coupon by ID
    @GetMapping("/{id}")
    public Coupon getCouponById(@PathVariable Long id) {
        return couponService.getCouponById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));
    }

    // POST create new coupon
    @PostMapping
    public Coupon createCoupon(@RequestBody Coupon coupon) {
        return couponService.createCoupon(coupon);
    }

    // PUT update coupon
    @PutMapping("/{id}")
    public Coupon updateCoupon(@PathVariable Long id, @RequestBody Coupon coupon) {
        return couponService.updateCoupon(id, coupon);
    }

    // DELETE coupon
    @DeleteMapping("/{id}")
    public void deleteCoupon(@PathVariable Long id) {
        couponService.deleteCoupon(id);
    }

    // POST: Apply coupon to cart
    @PostMapping("/apply/{couponId}")
    public double applyCouponToCart(@PathVariable Long couponId, @RequestBody Cart cart) {
        return couponService.applyCouponToCart(couponId, cart);
    }

    // POST: Get all applicable coupons for cart
    @PostMapping("/applicable")
    public List<Coupon> getApplicableCoupons(@RequestBody Cart cart) {
        return couponService.getApplicableCoupons(cart);
    }
}
