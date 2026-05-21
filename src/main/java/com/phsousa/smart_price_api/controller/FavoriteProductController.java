package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.FavoriteProductRequestDTO;
import com.phsousa.smart_price_api.dto.response.FavoriteProductResponseDTO;
import com.phsousa.smart_price_api.service.FavoriteProductService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/favorite-products")
@RequiredArgsConstructor
@Tag(
        name = "Produtos Favoritos",
        description = "Endpoints para gerenciamento de favoritos"
)
public class FavoriteProductController {

    private final FavoriteProductService service;

    @PostMapping
    @PreAuthorize("hasAuthority('PRODUCT_WRITE')")
    public ResponseEntity<FavoriteProductResponseDTO> create(
            @RequestBody @Valid FavoriteProductRequestDTO dto
    ) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(service.create(dto));
    }

    @GetMapping("/user/{userId}")
    @PreAuthorize("hasAuthority('PRODUCT_READ')")
    public ResponseEntity<List<FavoriteProductResponseDTO>>
    findByUser(
            @PathVariable UUID userId
    ) {

        return ResponseEntity.ok(
                service.findByUser(userId)
        );
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('PRODUCT_DELETE')")
    public ResponseEntity<String> delete(
            @PathVariable UUID id
    ) {

        service.delete(id);

        return ResponseEntity.ok(
                "Favorito removido com sucesso"
        );
    }
}