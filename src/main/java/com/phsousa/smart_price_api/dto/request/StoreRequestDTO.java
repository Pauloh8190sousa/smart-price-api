package com.phsousa.smart_price_api.dto.request;

import com.phsousa.smart_price_api.util.ValidationConstants;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StoreRequestDTO {

    @NotBlank(message = "Nome é obrigatório")
    @Size(min = 2, max = 100, message = "Nome deve ter no máximo 100 caracteres")
    private String name;

    @NotBlank(message = "Website é obrigatório")
    @Pattern(
            regexp = ValidationConstants.HTTP_URL,
            message = "URL inválida"
    )
    private String websiteUrl;

    @Pattern(
            regexp = ValidationConstants.HTTP_URL,
            message = "URL da logo inválida"
    )
    private String logoUrl;

    private Boolean active;
}
