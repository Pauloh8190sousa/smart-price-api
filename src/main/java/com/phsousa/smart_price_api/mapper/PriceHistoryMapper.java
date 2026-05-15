package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.response.PriceHistoryResponseDTO;
import com.phsousa.smart_price_api.entity.PriceHistory;

public class PriceHistoryMapper {

    public static PriceHistoryResponseDTO toDTO(
            PriceHistory entity
    ) {

        return new PriceHistoryResponseDTO(
                entity.getId(),
                entity.getOldPrice(),
                entity.getNewPrice(),
                entity.getChangedAt(),
                entity.getProduct().getId(),
                entity.getProduct().getName(),
                entity.getStore().getId(),
                entity.getStore().getName()
        );
    }
}