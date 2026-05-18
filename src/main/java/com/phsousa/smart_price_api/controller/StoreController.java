package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.StoreRequestDTO;
import com.phsousa.smart_price_api.dto.response.StoreResponseDTO;
import com.phsousa.smart_price_api.entity.Store;
import com.phsousa.smart_price_api.mapper.StoreMapper;
import com.phsousa.smart_price_api.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {

    private final StoreService storeService;

    @PostMapping
    @PreAuthorize("hasAuthority('STORE_WRITE')")
    public ResponseEntity<StoreResponseDTO> create(@RequestBody @Valid StoreRequestDTO request) {
        Store store = StoreMapper.toEntity(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(storeService.create(store));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('STORE_READ')")
    public ResponseEntity<List<StoreResponseDTO>> findAll() {
        return ResponseEntity.ok(storeService.findAll());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STORE_READ')")
    public ResponseEntity<StoreResponseDTO> findById(@PathVariable UUID id) {
        return ResponseEntity.ok(storeService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('STORE_UPDATE')")
    public ResponseEntity<StoreResponseDTO> update(
            @PathVariable UUID id,
            @RequestBody StoreRequestDTO request
    ) {
        Store updated = StoreMapper.toEntity(request);
        return ResponseEntity.ok(storeService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('STORE_DELETE')")
    public ResponseEntity<String> delete(@PathVariable UUID id) {
        storeService.delete(id);
        return ResponseEntity.ok("Store removido com sucesso");
    }
}
