package com.phsousa.smart_price_api.repository;

import com.phsousa.smart_price_api.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> {

    Optional<Product> findBySlug(String slug);

    boolean existsBySlug(String slug);


    boolean existsByNameIgnoreCaseAndBrandIgnoreCaseAndModelIgnoreCase(
            String name,
            String brand,
            String model
    );

    boolean existsBySlugAndIdNot(String slug, UUID id);


    boolean existsByNameIgnoreCaseAndBrandIgnoreCaseAndModelIgnoreCaseAndIdNot(
            String name,
            String brand,
            String model,
            UUID id
    );

}
