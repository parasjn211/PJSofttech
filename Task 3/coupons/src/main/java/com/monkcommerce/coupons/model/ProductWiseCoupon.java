package com.monkcommerce.coupons.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@DiscriminatorValue("PRODUCT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductWiseCoupon extends Coupon{
    @ElementCollection
    private List<Long> eligibleProductIds;

    private double discountPerProduct;

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getItems().stream()
                .anyMatch(item -> eligibleProductIds.contains(item.getProductId())) && this.isActive();
    }

    @Override
    public double applyDiscount(Cart cart) {
        return cart.getItems().stream()
                .filter(item -> eligibleProductIds.contains(item.getProductId()))
                .mapToDouble(item -> item.getQuantity() * discountPerProduct)
                .sum();
    }
}
