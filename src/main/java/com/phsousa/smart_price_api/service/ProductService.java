package com.phsousa.smart_price_api.service;

import com.phsousa.smart_price_api.entity.Product;

import java.util.List;
import java.util.UUID;

public interface ProductService {

    Product create(Product product);

    List<Product> findAll();

    Product findById(UUID id);

    void delete(UUID id);

    Product findBySlug (String slug);
}