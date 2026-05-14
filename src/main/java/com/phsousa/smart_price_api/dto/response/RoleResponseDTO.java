package com.phsousa.smart_price_api.dto.response;
import java.util.Set;

public record RoleResponseDTO(
        String name,
        Set<PermissionResponseDTO> permissions
) {}
