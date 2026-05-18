package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.request.FavoriteProductRequestDTO;
import com.phsousa.smart_price_api.dto.response.FavoriteProductResponseDTO;

import java.util.List;
import java.util.UUID;

public interface FavoriteProductService {

    FavoriteProductResponseDTO create(
            FavoriteProductRequestDTO dto
    );

    List<FavoriteProductResponseDTO> findByUser(
            UUID userId
    );

    void delete(UUID id);
}