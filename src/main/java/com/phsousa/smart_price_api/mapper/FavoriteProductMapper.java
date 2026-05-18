package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.response.FavoriteProductResponseDTO;
import com.phsousa.smart_price_api.entity.FavoriteProduct;

public class FavoriteProductMapper {

    public static FavoriteProductResponseDTO toDTO(
            FavoriteProduct entity
    ) {

        return new FavoriteProductResponseDTO(
                entity.getId(),
                entity.getUser().getId(),
                entity.getProduct().getId(),
                entity.getProduct().getName()
        );
    }
}