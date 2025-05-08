package com.monkcommerce.coupons.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("CART")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CartWiseCoupon extends Coupon{
    private double minCartValue;
    private double discountAmount;

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getTotal() >= minCartValue && this.isActive();
    }

    @Override
    public double applyDiscount(Cart cart) {
        return isApplicable(cart) ? discountAmount : 0;
    }
}
