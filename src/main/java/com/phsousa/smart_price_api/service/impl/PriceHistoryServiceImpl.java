package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.response.PriceHistoryResponseDTO;
import com.phsousa.smart_price_api.mapper.PriceHistoryMapper;
import com.phsousa.smart_price_api.repository.PriceHistoryRepository;
import com.phsousa.smart_price_api.service.PriceHistoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PriceHistoryServiceImpl implements PriceHistoryService {

    private final PriceHistoryRepository priceHistoryRepository;

    @Override
    public List<PriceHistoryResponseDTO> findByProduct(
            UUID productId
    ) {

        return priceHistoryRepository.findByProductId(productId)
                .stream()
                .map(PriceHistoryMapper::toDTO)
                .toList();
    }

    @Override
    public List<PriceHistoryResponseDTO> findByStore(
            UUID storeId
    ) {

        return priceHistoryRepository.findByStoreId(storeId)
                .stream()
                .map(PriceHistoryMapper::toDTO)
                .toList();
    }
}