package com.phsousa.smart_price_api.util;

import io.jsonwebtoken.Jwts;

import javax.crypto.SecretKey;

public class GenerateKey {

    public static void main(String[] args) {

        SecretKey key = Jwts.SIG.HS256.key().build();

        String base64Key = java.util.Base64
                .getEncoder()
                .encodeToString(key.getEncoded());

        System.out.println(base64Key);
    }
}
