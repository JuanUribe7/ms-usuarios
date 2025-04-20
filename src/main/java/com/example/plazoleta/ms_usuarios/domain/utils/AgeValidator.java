package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.MinorAgeException;

import java.time.LocalDate;

public class AgeValidator {
    public static void validate(LocalDate birthDate) {
        if (birthDate == null || birthDate.isAfter(LocalDate.now().minusYears(18))) {
            throw new MinorAgeException("El usuario debe ser mayor de edad");
        }
    }
}
