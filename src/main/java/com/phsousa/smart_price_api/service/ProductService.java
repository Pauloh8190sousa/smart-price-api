package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.dto.response.ProductResponseDTO;
import com.phsousa.smart_price_api.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    ProductResponseDTO create(Product product);

    List<ProductResponseDTO> findAll();

    ProductResponseDTO findById(UUID id);

    void delete(UUID id);

    ProductResponseDTO findBySlug(String slug);

    ProductResponseDTO update(UUID id, Product updated);
}