package com.phsousa.smart_price_api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class PriceAlertRequestDTO {

    @NotNull
    @DecimalMin(value = "0.0")
    private BigDecimal targetPrice;

    @NotNull
    private UUID userId;

    @NotNull
    private UUID productId;
}