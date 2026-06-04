package com.phsousa.smart_price_api.service;

import java.math.BigDecimal;

public interface EmailService {

    void sendPriceAlert(
            String to,
            String productName,
            BigDecimal currentPrice,
            BigDecimal targetPrice
    );

    void sendEmail(String to, String subject, String html);

    void sendWelcomeEmail(String to, String name);

}
