package com.phsousa.smart_price_api.dto.response;

import java.math.BigDecimal;

public record ProductPriceStatsResponseDTO(

        BigDecimal lowestPrice,

        BigDecimal highestPrice,

        BigDecimal averagePrice,

        Long storesCount

) {}
