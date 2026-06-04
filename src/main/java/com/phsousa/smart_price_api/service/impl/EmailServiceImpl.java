package com.phsousa.smart_price_api.service.impl;

import com.phsousa.smart_price_api.service.EmailService;

import com.phsousa.smart_price_api.util.FormatterUtil;
import com.resend.core.exception.ResendException;
import com.resend.services.emails.model.CreateEmailOptions;
import com.resend.services.emails.model.Template;
import org.springframework.stereotype.Service;


import com.resend.Resend;
import org.springframework.beans.factory.annotation.Value;

import java.math.BigDecimal;

@Service
public class EmailServiceImpl implements EmailService {

    private final Resend resend;

    public EmailServiceImpl(@Value("${resend.api-key}") String apiKey) {
        this.resend = new Resend(apiKey);
    }

    @Override
    public void sendPriceAlert(
            String to,
            String productName,
            BigDecimal currentPrice,
            BigDecimal targetPrice
    ) {

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from("Smart Price <onboarding@resend.dev>")
                .to(to)
                .subject("📉 Alerta de preço")
                .template(
                        Template.builder()
                                .id("price-alert")
                                .addVariable("product", productName)
                                .addVariable("price", FormatterUtil.formatCurrency(currentPrice))
                                .addVariable("targetPrice", FormatterUtil.formatCurrency(targetPrice))
                                .build()
                )
                .build();
        try {
            resend.emails().send(options);
        } catch (Exception e) {
            throw new RuntimeException("Erro ao enviar email de alerta de preço", e);
        }
    }

    @Override
    public void sendEmail(String to, String subject, String html) {

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from("Smart Price <onboarding@resend.dev>")
                .to(to)
                .subject(subject)
                .html(html)
                .build();

        try {
            resend.emails().send(options);
        } catch (ResendException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void sendWelcomeEmail(String to, String name) {

        CreateEmailOptions options = CreateEmailOptions.builder()
                .from("Smart Price <onboarding@resend.dev>")
                .to(to)
                .subject("Bem-vindo ao Smart Price 🎬")
                .template(
                        Template.builder()
                                .id("welcome-email")
                                .addVariable("name", name)
                                .build()
                )
                .build();

        try {
            resend.emails().send(options);
        } catch (ResendException e) {
            throw new RuntimeException(e);
        }
    }
}