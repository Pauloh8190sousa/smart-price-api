package com.phsousa.smart_price_api.dto.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PriceAlertResponseDTO(

        UUID id,
        BigDecimal targetPrice,
        Boolean active,
        LocalDateTime createdAt,

        UUID userId,
        String userName,

        UUID productId,
        String productName

) {
}
