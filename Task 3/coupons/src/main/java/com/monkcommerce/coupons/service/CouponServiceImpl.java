package com.monkcommerce.coupons.service;

import com.monkcommerce.coupons.model.Cart;
import com.monkcommerce.coupons.model.Coupon;
import com.monkcommerce.coupons.model.FlatCoupon;
import com.monkcommerce.coupons.model.PercentageCoupon;
import com.monkcommerce.coupons.repository.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CouponServiceImpl implements CouponService{
    private final CouponRepository couponRepository;

    @Autowired
    public CouponServiceImpl(CouponRepository couponRepository) {
        this.couponRepository = couponRepository;
    }

    @Override
    public List<Coupon> getAllCoupons() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> getCouponById(Long id) {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon createCoupon(Coupon coupon) {
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon updateCoupon(Long id, Coupon updatedCoupon) {
        Coupon existingCoupon = couponRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Coupon not found"));

        // copy base fields
        existingCoupon.setCode(updatedCoupon.getCode());
        existingCoupon.setStartDate(updatedCoupon.getStartDate());
        existingCoupon.setEndDate(updatedCoupon.getEndDate());
        existingCoupon.setActive(updatedCoupon.isActive());

        // Handle specific subclass
        if (existingCoupon instanceof FlatCoupon && updatedCoupon instanceof FlatCoupon) {
            FlatCoupon existing = (FlatCoupon) existingCoupon;
            FlatCoupon updated = (FlatCoupon) updatedCoupon;
            existing.setAmount(updated.getAmount());
            existing.setMinCartValue(updated.getMinCartValue());
        } else if (existingCoupon instanceof PercentageCoupon && updatedCoupon instanceof PercentageCoupon) {
            PercentageCoupon existing = (PercentageCoupon) existingCoupon;
            PercentageCoupon updated = (PercentageCoupon) updatedCoupon;
            existing.setDiscountPercentage(updated.getDiscountPercentage());
            existing.setMaxDiscount(updated.getMaxDiscount());
            existing.setCategoryName(updated.getCategoryName());
        } else {
            throw new RuntimeException("Coupon type mismatch or unsupported update");
        }

        return couponRepository.save(existingCoupon);
    }


    @Override
    public void deleteCoupon(Long id) {
        couponRepository.deleteById(id);
    }

    @Override
    public List<Coupon> getApplicableCoupons(Cart cart) {
        return couponRepository.findAll().stream()
                .filter(coupon -> coupon.isApplicable(cart))
                .collect(Collectors.toList());
    }

    @Override
    public double applyCouponToCart(Long couponId, Cart cart) {
        Optional<Coupon> couponOpt = couponRepository.findById(couponId);
        if (couponOpt.isEmpty()) throw new RuntimeException("Coupon not found");
        Coupon coupon = couponOpt.get();
        if (!coupon.isApplicable(cart)) throw new RuntimeException("Coupon not applicable");

        return coupon.applyDiscount(cart);
    }
}
