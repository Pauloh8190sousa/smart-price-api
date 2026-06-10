package com.phsousa.smart_price_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_PRODUCT_PRICE")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Column(nullable = false, precision = 12, scale = 2)
    private BigDecimal price;

    @Column(nullable = false, length = 1000)
    private String productUrl;

    @Column(nullable = false)
    private Boolean available;
    private String sellerName;
    private BigDecimal shippingPrice;
    private Integer installmentQuantity;
    private BigDecimal installmentValue;
    private LocalDateTime capturedAt;

    @ManyToOne(optional = false)
    private Product product;

    @ManyToOne(optional = false)
    private Store store;



}
