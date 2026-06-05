package com.phsousa.smart_price_api.dto.request;

import com.phsousa.smart_price_api.util.ValidationConstants;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.*;
import lombok.Data;

@Data
@Schema(description = "Dados para registro de usuário")
public class RegisterRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(
            min = 3,
            max = 150,
            message = "Nome deve possuir entre 3 e 150 caracteres"
    )
    private String name;

    @NotBlank(message = "Email é obrigatório")
    @Email(message = "Email inválido")
    @Size(
            max = 255,
            message = "Email deve possuir no máximo 255 caracteres"
    )
    private String email;

    @NotBlank(message = "Senha é obrigatória")
    @Size(
            min = 8,
            max = 100,
            message = "Senha deve possuir entre 8 e 100 caracteres"
    )
    @Pattern(
            regexp = ValidationConstants.STRONG_PASSWORD,
            message = """
                    A senha deve conter pelo menos:
                    1 letra maiúscula,
                    1 letra minúscula,
                    1 número
                    e 1 caractere especial
                    """
    )
    private String password;
}