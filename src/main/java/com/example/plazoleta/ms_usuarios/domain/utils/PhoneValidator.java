package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidPhoneException;

import java.util.regex.Pattern;

public class PhoneValidator {

    private PhoneValidator() {
        throw new UnsupportedOperationException("Instantiation not allowed");
    }
    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?\\d{1,12}$"); // Allows up to 12 digits after an optional '+'

    public static void validate(String phone) {
        if (phone == null || phone.trim().isEmpty()) {
            throw new InvalidPhoneException("El teléfono no puede estar vacío");
        }

        if (!PHONE_PATTERN.matcher(phone).matches()) {
            throw new InvalidPhoneException("El teléfono es inválido");
        }
    }
}