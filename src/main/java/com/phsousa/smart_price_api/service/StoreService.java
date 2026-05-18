package com.phsousa.smart_price_api.service;
import com.phsousa.smart_price_api.dto.response.StoreResponseDTO;
import com.phsousa.smart_price_api.entity.Store;

import java.util.List;
import java.util.UUID;

public interface StoreService {

    StoreResponseDTO create(Store store);

    List<StoreResponseDTO> findAll();

    StoreResponseDTO findById(UUID id);

    StoreResponseDTO update (UUID id, Store updated);

    void delete(UUID id);


}
