package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.response.PriceAlertResponseDTO;
import com.phsousa.smart_price_api.entity.PriceAlert;


public class PriceAlertMapper {

    public static PriceAlertResponseDTO toDTO(
            PriceAlert entity
    ) {

        return new PriceAlertResponseDTO(
                entity.getId(),
                entity.getTargetPrice(),
                entity.getActive(),
                entity.getCreatedAt(),

                entity.getUser().getId(),
                entity.getUser().getName(),

                entity.getProduct().getId(),
                entity.getProduct().getName()
        );
    }
}
