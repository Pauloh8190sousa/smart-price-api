package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.dto.response.ProductResponseDTO;
import com.phsousa.smart_price_api.entity.Product;
import com.phsousa.smart_price_api.exception.ResourceNotFoundException;
import com.phsousa.smart_price_api.mapper.ProductMapper;
import com.phsousa.smart_price_api.repository.ProductRepository;
import com.phsousa.smart_price_api.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponseDTO create(Product product) {

        Product entity = productRepository.save(product);

        return ProductMapper.toDTO(entity);
    }

    @Override
    public List<ProductResponseDTO> findAll() {
        return productRepository.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @Override
    public ProductResponseDTO findById(UUID id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado"));

        return ProductMapper.toDTO(product);
    }

    @Override
    public void delete(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public ProductResponseDTO findBySlug(String slug) {
        Product product = productRepository.findBySlug(slug)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Produto não encontrado"));

        return ProductMapper.toDTO(product);
    }

    @Override
    @Transactional
    public ProductResponseDTO update(UUID id, Product updated) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Produto não encontrado"));

        product.setUpdatedAt(LocalDateTime.now());
        product.setActive(updated.getActive());
        product.setBrand(updated.getBrand());
        product.setCategory(updated.getCategory());
        product.setDescription(updated.getDescription());
        product.setName(updated.getName());
        product.setSlug(updated.getSlug());
        product.setModel(updated.getModel());
        product.setImageUrl(updated.getImageUrl());

        return ProductMapper.toDTO(productRepository.save(product));
    }
}