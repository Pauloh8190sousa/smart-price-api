package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceStatsResponseDTO;

import java.util.List;
import java.util.UUID;

public interface ProductPriceService {
    ProductPriceResponseDTO create(ProductPriceRequestDTO dto);

    List<ProductPriceResponseDTO>  findByProduct(UUID productId);

    ProductPriceResponseDTO findLowestPrice(UUID productId);

    List<ProductPriceResponseDTO> findByStore(UUID storeId);

    ProductPriceStatsResponseDTO getStats(UUID productId);
}
