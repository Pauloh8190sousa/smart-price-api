package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.response.StoreResponseDTO;
import com.phsousa.smart_price_api.entity.Store;
import com.phsousa.smart_price_api.exception.BusinessException;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.mapper.StoreMapper;
import com.phsousa.smart_price_api.repository.StoreRepository;
import com.phsousa.smart_price_api.service.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;


    @Override
    public StoreResponseDTO create(Store store) {

        normalize(store);

        if (storeRepository.existsByNameIgnoreCase(store.getName())) {
            throw new BusinessException("Já existe uma loja com esse nome");
        }

        if (storeRepository.existsByWebsiteUrlIgnoreCase(store.getWebsiteUrl())) {
            throw new BusinessException(
                    "Já existe uma loja com esse site"
            );
        }

        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    public List<StoreResponseDTO> findAll() {
        return storeRepository.findAll()
                .stream()
                .map(StoreMapper::toDTO)
                .toList();
    }

    @Override
    public StoreResponseDTO findById(UUID id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loja não encontrada"));

        return StoreMapper.toDTO(store);
    }

    @Override
    @Transactional
    public StoreResponseDTO update(UUID id, Store updated) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Loja não encontrada"));


        normalize(updated);

        if (
                storeRepository.existsByNameIgnoreCaseAndIdNot(
                        updated.getName(),
                        id
                )
        ) {
            throw new BusinessException(
                    "Já existe uma loja com esse nome"
            );
        }

        if (
                storeRepository.existsByWebsiteUrlIgnoreCaseAndIdNot(
                        updated.getWebsiteUrl(),
                        id
                )
        ) {
            throw new BusinessException(
                    "Já existe uma loja com esse nome"
            );
        }

        store.setName(updated.getName());
        store.setWebsiteUrl(updated.getWebsiteUrl());
        store.setLogoUrl(updated.getLogoUrl());
        store.setActive(updated.getActive());

        return StoreMapper.toDTO(storeRepository.save(store));
    }

    @Override
    @Transactional
    public void delete(UUID id) {
        Store store = storeRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Loja não encontrada"));

        storeRepository.delete(store);
    }



    private void normalize(Store store) {

        store.setName(
                store.getName().trim().toUpperCase()
        );

        store.setWebsiteUrl(
                store.getWebsiteUrl().trim().toLowerCase()
        );
    }

}
