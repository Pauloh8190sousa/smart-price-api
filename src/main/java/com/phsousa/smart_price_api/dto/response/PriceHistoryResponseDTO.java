package com.phsousa.smart_price_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PriceHistoryResponseDTO(

        UUID id,

        BigDecimal oldPrice,

        BigDecimal newPrice,

        LocalDateTime changedAt,

        UUID productId,

        String productName,

        UUID storeId,

        String storeName

) {}