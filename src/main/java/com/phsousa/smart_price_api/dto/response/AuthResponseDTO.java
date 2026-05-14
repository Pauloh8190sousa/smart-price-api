package com.phsousa.smart_price_api.dto.response;


import java.util.Set;

public record AuthResponseDTO(
        String token,
        UserResponseDTO user,
        Set<RoleResponseDTO> roles
) {}