package com.phsousa.smart_price_api.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "T_PRICE_ALERT")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    private BigDecimal targetPrice;
    private Boolean active;
    private LocalDateTime createdAt;

    @ManyToOne
    private User user;
    @ManyToOne
    private Product product;


    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        active = true;
    }
}
