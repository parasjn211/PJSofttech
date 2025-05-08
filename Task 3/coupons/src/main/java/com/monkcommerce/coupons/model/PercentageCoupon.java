package com.monkcommerce.coupons.model;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class PercentageCoupon extends Coupon {
    private double discountPercentage;
    private double maxDiscount;
    private String categoryName;

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getItems().stream().anyMatch(
                item -> item.getCategory().equalsIgnoreCase(categoryName)
        );
    }

    @Override
    public double applyDiscount(Cart cart) {
        if (!isApplicable(cart)) return 0.0;
        double discount = (cart.getTotal() * discountPercentage) / 100.0;
        return Math.min(discount, maxDiscount);
    }
}
