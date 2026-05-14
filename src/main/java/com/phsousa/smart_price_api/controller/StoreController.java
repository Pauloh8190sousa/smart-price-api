package com.phsousa.smart_price_api.controller;

import com.phsousa.smart_price_api.dto.request.StoreRequestDTO;
import com.phsousa.smart_price_api.dto.response.StoreResponseDTO;
import com.phsousa.smart_price_api.entity.Store;
import com.phsousa.smart_price_api.mapper.StoreMapper;
import com.phsousa.smart_price_api.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('STORE_WRITE')")
    public StoreResponseDTO create(@RequestBody @Valid StoreRequestDTO request) {
        Store store = StoreMapper.toEntity(request);
        return StoreMapper.toDTO(storeService.create(store));
    }

    @GetMapping
    @PreAuthorize("hasAuthority('STORE_READ')")
    public List<StoreResponseDTO> findAll() {
        return storeService.findAll()
                .stream()
                .map(StoreMapper::toDTO)
                .toList();
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('STORE_READ')")
    public StoreResponseDTO findById(@PathVariable UUID id) {
        return StoreMapper.toDTO(storeService.findById(id));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('STORE_UPDATE')")
    public StoreResponseDTO update(
            @PathVariable UUID id,
            @RequestBody StoreRequestDTO request
    ) {
        Store updated = StoreMapper.toEntity(request);
        return StoreMapper.toDTO(storeService.update(id, updated));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('STORE_DELETE')")
    public void delete(@PathVariable UUID id) {
        storeService.delete(id);
    }
}
