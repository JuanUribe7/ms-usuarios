package com.example.plazoleta.ms_usuarios.domain.exceptions;

public class InvalidIdentityDocumentException extends RuntimeException {
    public InvalidIdentityDocumentException(String message) {
        super(message);
    }
}
