package com.phsousa.smart_price_api.dto.request;

import com.phsousa.smart_price_api.util.ValidationConstants;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

import java.math.BigDecimal;
import java.util.UUID;

@Data
public class ProductPriceRequestDTO {

    @NotNull(message = "Preço é obrigatório")
    @DecimalMin(value = "0.0", inclusive = false,
            message = "Preço deve ser maior que zero")
    private BigDecimal price;

    @NotBlank(message = "URL do produto é obrigatória")
    @Pattern(
            regexp = ValidationConstants.HTTP_URL,
            message = "URL inválida"
    )
    private String productUrl;

    @NotNull(message = "Disponibilidade é obrigatória")
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