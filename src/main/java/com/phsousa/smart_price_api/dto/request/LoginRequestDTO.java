package com.phsousa.smart_price_api.dto.request;

import lombok.Data;

@Data
public class LoginRequestDTO {

    private String email;
    private String password;

}