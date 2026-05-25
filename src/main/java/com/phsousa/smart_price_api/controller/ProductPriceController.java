package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.ProductPriceRequestDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceResponseDTO;
import com.phsousa.smart_price_api.dto.response.ProductPriceStatsResponseDTO;
import com.phsousa.smart_price_api.service.ProductPriceService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/product-prices")
@RequiredArgsConstructor
public class ProductPriceController {

    private final ProductPriceService productPriceService;

    @PostMapping
    @PreAuthorize("hasAuthority('PRODUCT_WRITE') and hasAuthority('STORE_WRITE')")
    public ResponseEntity<ProductPriceResponseDTO> create(
            @RequestBody @Valid ProductPriceRequestDTO dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productPriceService.create(dto));
    }

    @GetMapping("/product/{productId}")
    @PreAuthorize("hasAuthority('PRODUCT_READ') and hasAuthority('STORE_READ')")
    public ResponseEntity<List<ProductPriceResponseDTO>>
    findByProduct(
            @PathVariable UUID productId
    ) {

        return ResponseEntity.ok(
                productPriceService.findByProduct(productId)
        );
    }

    @GetMapping("/product/{productId}/lowest")
    @PreAuthorize("hasAuthority('PRODUCT_READ') and hasAuthority('STORE_READ')")
    public ResponseEntity<ProductPriceResponseDTO>
    findLowestPrice(
            @PathVariable UUID productId
    ) {

        return ResponseEntity.ok(
                productPriceService.findLowestPrice(productId)
        );
    }

    @GetMapping("/store/{storeId}")
    @PreAuthorize("hasAuthority('STORE_READ') and hasAuthority('PRODUCT_READ')")
    public ResponseEntity<List<ProductPriceResponseDTO>>
    findByStore(
            @PathVariable UUID storeId
    ) {

        return ResponseEntity.ok(
                productPriceService.findByStore(storeId)
        );
    }


    @GetMapping("/product/{productId}/stats")
    @PreAuthorize("hasAuthority('PRODUCT_READ') and hasAuthority('STORE_READ')")
    public ResponseEntity<ProductPriceStatsResponseDTO> stats(
            @PathVariable UUID productId
    ) {

        return ResponseEntity.ok(
                productPriceService.getStats(productId)
        );
    }
}