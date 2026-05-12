package com.phsousa.smart_price_api.dto.response;
import com.phsousa.smart_price_api.entity.Role;
import lombok.Builder;
import lombok.Data;

import java.util.UUID;

@Data
@Builder
public class UserResponseDTO {

    private UUID id;
    private String name;
    private String email;
    private Role role;

}