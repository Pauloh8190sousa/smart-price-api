package com.phsousa.smart_price_api.dto.request;

import lombok.Data;

@Data
public class StoreRequestDTO {

    private String name;
    private String websiteUrl;
    private String logoUrl;
    private Boolean active;
}
