package com.example.plazoleta.ms_usuarios.owner.domain.exceptions;

public class InvalidPhoneException extends RuntimeException {
    public InvalidPhoneException(String message) {
        super(message);
    }
}
