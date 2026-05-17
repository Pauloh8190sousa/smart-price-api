package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.request.PriceAlertRequestDTO;
import com.phsousa.smart_price_api.dto.response.PriceAlertResponseDTO;

import java.util.List;
import java.util.UUID;

public interface PriceAlertService {

    PriceAlertResponseDTO create(PriceAlertRequestDTO dto);
    List<PriceAlertResponseDTO>  findByUser(UUID userId);
    void delete(UUID id);
    PriceAlertResponseDTO toggle(UUID id);

}
