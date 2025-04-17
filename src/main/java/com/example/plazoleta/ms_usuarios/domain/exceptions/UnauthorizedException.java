package com.example.plazoleta.ms_usuarios.domain.exceptions;

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String message) {
        super(message);

    }
}
