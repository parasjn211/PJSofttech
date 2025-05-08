
#  Coupon Management API (Monk Commerce 2024 Task)

##  Objective
This project implements a RESTful API to manage and apply different types of discount coupons  **cart-wise**, **product-wise**, and **Buy X Get Y (BxGy)**  for an e-commerce platform. The system is designed to be modular and extensible for future coupon types.

##  Tech Stack
- Java 17
- Spring Boot 3.x
- Spring Data JPA
- MySQL
- Lombok
- Postman for testing

##  Implemented Features

### 1. Coupon CRUD API
- `POST /api/coupons` — Create coupon (`FlatCoupon` or `PercentageCoupon`)
- `GET /api/coupons` — Fetch all coupons
- `GET /api/coupons/{id}` — Fetch a specific coupon
- `PUT /api/coupons/{id}` — Update a coupon (type-aware)
- `DELETE /api/coupons/{id}` — Delete a coupon

### 2. Apply Coupon
- `POST /api/coupons/apply/{couponId}` — Apply a coupon to a cart and return discount

### 3. Get Applicable Coupons
- `POST /api/coupons/applicable` — Return all applicable coupons for a given cart

##  Use Cases

| Type             | Example                                                                 |
|------------------|-------------------------------------------------------------------------|
| Flat Coupon       | Rs.100 off if cart total ≥ Rs.500                                           |
| Percentage Coupon | 10% off on category "Electronics", max ₹200                             |
| BxGy Coupon       | _Not implemented due to time constraint_                                |

##  Coupon Structure

```json
{
  "coupon_type": "FlatCoupon",
  "code": "FLAT100",
  "startDate": "2025-05-01",
  "endDate": "2025-06-01",
  "active": true,
  "amount": 100.0,
  "minCartValue": 500.0
}
```

##  How to Run

1. Configure MySQL credentials in `application.properties`
2. Run the project with `mvn spring-boot:run`
3. Use Postman to test endpoints

##  Sample Cart Payload

```json
{
  "items": [
    {
      "name": "Rice",
      "price": 80,
      "quantity": 5,
      "category": "Grocery"
    },
    {
      "name": "Shampoo",
      "price": 120,
      "quantity": 2,
      "category": "Toiletries"
    }
  ]
}
```

##  Assumptions

- Coupons are only valid if:
  - `active == true`
  - current date is within `startDate` and `endDate`
- Cart total includes all products
- Each coupon type has its own applicability logic

##  Unimplemented (Due to Time Constraints)

- **BxGy coupons** (e.g., Buy 2 of X, get 1 of Y)
- Coupon expiry checks on DB level
- Admin login/authentication
- PATCH for partial updates

##  Limitations

- Only supports `FlatCoupon` and `PercentageCoupon`
- PUT requires the full object to avoid overwriting fields
- No frontend — Postman is used for testing

## Future Enhancements

- Add support for `BxGyCoupon`
- Add `PATCH` endpoint for partial updates
- Add admin authentication & role-based access
- Add Swagger UI for documentation
