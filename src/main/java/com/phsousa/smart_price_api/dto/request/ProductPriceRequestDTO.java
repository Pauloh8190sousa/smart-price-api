package com.phsousa.smart_price_api.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductPriceRequestDTO {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Preço deve ser maior que zero")
    private BigDecimal price;

    private String productUrl;

    private Boolean available;

    private String sellerName;

    @DecimalMin(value = "0.0", inclusive = true,
            message = "Frete inválido")
    private BigDecimal shippingPrice;

    private Integer installmentQuantity;

    private BigDecimal installmentValue;

    @NotNull(message = "Produto é obrigatório")
    private UUID productId;

    @NotNull(message = "Loja é obrigatória")
    private UUID storeId;
}