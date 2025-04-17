package com.example.plazoleta.ms_usuarios.domain.exceptions;

public class MinorAgeException extends RuntimeException {
    public MinorAgeException(String message) {
        super(message);
    }
}
