package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.*;

import com.example.plazoleta.ms_usuarios.domain.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserValidator {

    private static final Pattern EMAIL_PATTERN =
            Pattern.compile("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$");

    private static final Pattern PHONE_PATTERN =
            Pattern.compile("^\\+?\\d{7,15}$");

    public static void validate(User user) {
        if (user.getName() == null || user.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("El nombre no puede estar vacío");
        }

        if (user.getEmail() == null || !EMAIL_PATTERN.matcher(user.getEmail()).matches()) {
            throw new InvalidEmailException("Correo electrónico inválido");
        }

        if (user.getPhone() == null || !PHONE_PATTERN.matcher(user.getPhone()).matches()) {
            throw new InvalidPhoneException("Teléfono inválido");
        }

        if (user.getIdentityDocument() == null || !user.getIdentityDocument().matches("\\d+")) {
            throw new InvalidIdentityDocumentException("Documento de identidad inválido");
        }

        if (user.getBirthDate() == null || user.getBirthDate().isAfter(LocalDate.now().minusYears(18))) {
            throw new MinorAgeException("El usuario debe ser mayor de edad");
        }

        if (user.getRole() == null) {
            throw new IllegalArgumentException("El rol no puede ser nulo");
        }
    }
}
