package com.phsousa.smart_price_api.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
@Schema(description = "Dados para autenticação")
public class LoginRequestDTO {

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Schema(
            description = "Email do usuário",
            example = "paulo@email.com"
    )
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Schema(
            description = "Senha do usuário",
            example = "Senha123"
    )
    private String password;
}