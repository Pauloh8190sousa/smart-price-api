package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.entity.Store;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.repository.StoreRepository;
import com.phsousa.smart_price_api.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    @Override
    public Store create(Store store) {
        return storeRepository.save(store);
    }

    @Override
    public List<Store> findAll() {
        return storeRepository.findAll();
    }

    @Override
    public Store findById(UUID id) {
        return storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Store não encontrada"));
    }

    @Override
    public Store update(UUID id, Store updated) {
        Store store = findById(id);

        store.setName(updated.getName());
        store.setWebsiteUrl(updated.getWebsiteUrl());
        store.setLogoUrl(updated.getLogoUrl());
        store.setActive(updated.getActive());

        return storeRepository.save(store);
    }

    @Override
    public void delete(UUID id) {
        storeRepository.deleteById(id);
    }

}
