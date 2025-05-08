package com.monkcommerce.coupons.model;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDate;

@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "coupon_type"
)
@JsonSubTypes({
        @JsonSubTypes.Type(value = FlatCoupon.class, name = "FlatCoupon"),
        @JsonSubTypes.Type(value = PercentageCoupon.class, name = "PercentageCoupon")
})
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "coupon_type")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public abstract class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;

    private LocalDate startDate;

    private LocalDate endDate;

    private boolean active;

    public abstract boolean isApplicable(Cart cart);

    public abstract double applyDiscount(Cart cart);
}
