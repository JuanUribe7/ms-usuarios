package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidEmailException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidIdentityDocumentException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidPhoneException;
import com.example.plazoleta.ms_usuarios.domain.exceptions.MinorAgeException;
import com.example.plazoleta.ms_usuarios.domain.model.Role;
import org.junit.jupiter.api.Test;

import com.example.plazoleta.ms_usuarios.domain.model.User;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class UserValidatorTest {

    @Test
    void whenAllFieldsValid_thenNoException() {
        User user = new User();
        user.setEmail("user@example.com");
        user.setPhone("+123456789");
        user.setIdentityDocument("1234567890");
        user.setBirthDate(LocalDate.now().minusYears(25));
        user.setRole(Role.OWNER);


        assertDoesNotThrow(() -> UserValidator.validate(user));
    }

    // Email inválido
    @Test
    void whenEmailNull_thenInvalidEmailException() {
        User user = baseUser();
        user.setEmail(null);
        assertThrows(InvalidEmailException.class, () -> UserValidator.validate(user));
    }

    @Test
    void whenEmailMalformed_thenInvalidEmailException() {
        User user = baseUser();
        user.setEmail("invalid-email");
        assertThrows(InvalidEmailException.class, () -> UserValidator.validate(user));
    }

    // Teléfono inválido
    @Test
    void whenPhoneNull_thenInvalidPhoneException() {
        User user = baseUser();
        user.setPhone(null);
        assertThrows(InvalidPhoneException.class, () -> UserValidator.validate(user));
    }

    @Test
    void whenPhoneMalformed_thenInvalidPhoneException() {
        User user = baseUser();
        user.setPhone("abcd1234");
        assertThrows(InvalidPhoneException.class, () -> UserValidator.validate(user));
    }

    // Documento de identidad inválido
    @Test
    void whenDocumentNull_thenInvalidDocumentException() {
        User user = baseUser();
        user.setIdentityDocument(null);
        assertThrows(InvalidIdentityDocumentException.class, () -> UserValidator.validate(user));
    }

    @Test
    void whenDocumentNonNumeric_thenInvalidDocumentException() {
        User user = baseUser();
        user.setIdentityDocument("abc123");
        assertThrows(InvalidIdentityDocumentException.class, () -> UserValidator.validate(user));
    }

    // Edad inválida (<18)
    @Test
    void whenBirthDateNull_thenMinorAgeException() {
        User user = baseUser();
        user.setBirthDate(null);
        assertThrows(MinorAgeException.class, () -> UserValidator.validate(user));
    }

    @Test
    void whenUnder18_thenMinorAgeException() {
        User user = baseUser();
        user.setBirthDate(LocalDate.now().minusYears(17));
        assertThrows(MinorAgeException.class, () -> UserValidator.validate(user));
    }

    // Asociación a restaurante para OWNER o EMPLOYEE




    // Helper para crear un usuario base válido
    private User baseUser() {
            User user = new User();
            user.setName("Carlos"); // 👈 AGREGA ESTO
            user.setEmail("user@example.com");
            user.setPhone("+123456789");
            user.setIdentityDocument("1234567890");
            user.setBirthDate(LocalDate.now().minusYears(25));
            user.setRole(Role.OWNER);

            return user;
        }

    }
