package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidEmailException;

import java.util.regex.Pattern;

public class EmailValidator {



    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    public static void validate(String email) {

        if (email == null || email.isBlank()) {
            throw new InvalidEmailException("El correo no puede estar vacío.");
        }

        if (!email.contains("@")) {
            throw new InvalidEmailException("El correo debe contener '@'.");
        }

        String[] parts = email.split("@");

        if (parts.length != 2 || parts[0].isBlank()) {
            throw new InvalidEmailException("Debe haber texto después del '@'.");
        }

        if (!parts[1].contains(".")) {
            throw new InvalidEmailException("El dominio debe tener una extensión, por ejemplo '.com'.");
        }

        if (email == null || !EMAIL_PATTERN.matcher(email).matches()) {
            throw new InvalidEmailException("Correo electrónico inválido");
        }
    }
}
