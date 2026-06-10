package com.phsousa.smart_price_api.util;

public final class ValidationConstants {

    private ValidationConstants() {}

    public static final String STRONG_PASSWORD =
            "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&._#\\-])[A-Za-z\\d@$!%*?&._#\\-]{8,100}$";

    public static final String HTTP_URL =
            "^(http|https)://.*$";

    public static final String SLUG =
            "^[a-z0-9]+(?:-[a-z0-9]+)*$";
}
