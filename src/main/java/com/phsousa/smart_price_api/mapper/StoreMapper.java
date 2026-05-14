package com.phsousa.smart_price_api.mapper;

import com.phsousa.smart_price_api.dto.request.StoreRequestDTO;
import com.phsousa.smart_price_api.dto.response.StoreResponseDTO;
import com.phsousa.smart_price_api.entity.Store;

public class StoreMapper {

    public static Store toEntity(StoreRequestDTO dto) {
        return Store.builder()
                .name(dto.getName())
                .websiteUrl(dto.getWebsiteUrl())
                .logoUrl(dto.getLogoUrl())
                .active(dto.getActive() != null ? dto.getActive() : true)
                .build();
    }

    public static StoreResponseDTO toDTO(Store store) {
        return new StoreResponseDTO(
                store.getId(),
                store.getName(),
                store.getWebsiteUrl(),
                store.getLogoUrl(),
                store.getActive()
        );
    }
}
