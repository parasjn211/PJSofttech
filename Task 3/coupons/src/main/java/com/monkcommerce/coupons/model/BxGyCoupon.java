package com.monkcommerce.coupons.model;

public class BxGyCoupon extends Coupon{

    private Long productId;
    private int xQty;
    private int yQty;
    private int maxRepeats;

    @Override
    public boolean isApplicable(Cart cart) {
        return cart.getItems().stream()
                .anyMatch(item -> item.getProductId().equals(productId) && item.getQuantity() >= xQty)
                && this.isActive();
    }

    @Override
    public double applyDiscount(Cart cart) {
        return cart.getItems().stream()
                .filter(item -> item.getProductId().equals(productId))
                .mapToDouble(item -> {
                    int eligibleRepeats = item.getQuantity() / xQty;
                    eligibleRepeats = Math.min(eligibleRepeats, maxRepeats);
                    return eligibleRepeats * yQty * item.getPrice();
                }).sum();
    }
}
