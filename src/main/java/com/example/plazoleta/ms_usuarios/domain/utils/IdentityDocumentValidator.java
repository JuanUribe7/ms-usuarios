package com.example.plazoleta.ms_usuarios.domain.utils;

import com.example.plazoleta.ms_usuarios.domain.exceptions.InvalidIdentityDocumentException;

public class IdentityDocumentValidator {

    public static void validate(String identityDocument){
        if (identityDocument == null || !identityDocument.matches("\\d+")) {
            throw new InvalidIdentityDocumentException("Documento de identidad inválido");
        }
    }
}
