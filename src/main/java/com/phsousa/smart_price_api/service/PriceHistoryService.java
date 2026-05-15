package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.response.PriceHistoryResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PriceHistoryService {

    List<PriceHistoryResponseDTO>  findByProduct(UUID productId);

    List<PriceHistoryResponseDTO> findByStore(UUID storeId);
}
