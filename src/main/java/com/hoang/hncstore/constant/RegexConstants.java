package com.hoang.hncstore.constant;

public class RegexConstants {
    public static final String PHONE_NUMBER_REGEX = "^0\\d{9}$";
    public static final String PASSWORD_REGEX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*[^a-zA-Z0-9]).+$";
    public static final String USERNAME_REGEX = "^[a-zA-Z0-9]+$";
}
