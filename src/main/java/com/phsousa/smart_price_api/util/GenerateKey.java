package com.phsousa.smart_price_api.util;

import io.jsonwebtoken.Jwts;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.crypto.SecretKey;
import java.util.Scanner;

public class GenerateKey {

    public static void main(String[] args) {

        generatePassword();

        //scretKeyJWT();

    }


    private static void scretKeyJWT() {
        SecretKey key = Jwts.SIG.HS256.key().build();

        String base64Key = java.util.Base64
                .getEncoder()
                .encodeToString(key.getEncoded());

        System.out.println(base64Key);
    }


    private static void generatePassword() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Digite a sua senha: ");
        String password = scanner.nextLine();


        BCryptPasswordEncoder encoder =
                new BCryptPasswordEncoder();

        System.out.println(
                encoder.encode(password)
        );
    }
}
