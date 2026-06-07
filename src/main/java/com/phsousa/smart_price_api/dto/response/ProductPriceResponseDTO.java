package com.phsousa.smart_price_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record ProductPriceResponseDTO(

        UUID id,

        BigDecimal price,

        String productUrl,

        Boolean available,

        String sellerName,

        BigDecimal shippingPrice,

        Integer installmentQuantity,

        BigDecimal installmentValue,

        LocalDateTime capturedAt,

        UUID productId,

        String productName,

        UUID storeId,

        String storeName,

        String productSlug,

        String storeLogoUrl

) {}