package com.monkcommerce.coupons.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class FlatCoupon extends Coupon {
    private double amount;
    private double minCartValue;

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getTotal() >= minCartValue;
    }

    @Override
    public double applyDiscount(Cart cart) {
        return isApplicable(cart) ? amount : 0.0;
    }
}
