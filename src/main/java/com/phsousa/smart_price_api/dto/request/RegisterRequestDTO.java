package com.phsousa.smart_price_api.dto.request;

import lombok.Data;

@Data
public class RegisterRequestDTO {

    private String name;
    private String email;
    private String password;

}