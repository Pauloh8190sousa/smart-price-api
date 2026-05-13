package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.ProductRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductResponseDTO;
import com.phsousa.smart_price_api.entity.Product;
import com.phsousa.smart_price_api.mapper.ProductMapper;
import com.phsousa.smart_price_api.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ProductResponseDTO create(
            @Valid @RequestBody ProductRequestDTO request
    ) {

        Product product = ProductMapper.toEntity(request);

        Product createdProduct = productService.create(product);

        return ProductMapper.toDTO(createdProduct);
    }

    @GetMapping
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public List<ProductResponseDTO> findAll() {

        return productService.findAll()
                .stream()
                .map(ProductMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ProductResponseDTO findById(
            @PathVariable UUID id
    ) {

        Product product = productService.findById(id);

        return ProductMapper.toDTO(product);
    }

    @GetMapping("/slug/{slug}")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ProductResponseDTO findBySlug(
            @PathVariable String slug
    ) {

        Product product = productService.findBySlug(slug);

        return ProductMapper.toDTO(product);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(
            @PathVariable UUID id
    ) {

        productService.delete(id);
    }
}