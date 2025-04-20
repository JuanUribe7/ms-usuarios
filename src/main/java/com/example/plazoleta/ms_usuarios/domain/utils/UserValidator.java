package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.*;

import com.example.plazoleta.ms_usuarios.domain.model.User;

import java.time.LocalDate;
import java.util.regex.Pattern;

public class UserValidator {

    public static void validate(User user) {
            if (user.getName() == null || user.getName().trim().isEmpty()) {
                throw new IllegalArgumentException("El nombre no puede estar vacío");
            }
            EmailValidator.validate(user.getEmail());
            PhoneValidator.validate(user.getPhone());
            IdentityDocumentValidator.validate(user.getIdentityDocument());
            AgeValidator.validate(user.getBirthDate());
            RoleValidator.validate(user.getRole());

    }
}
