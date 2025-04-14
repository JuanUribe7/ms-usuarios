package com.example.plazoleta.ms_usuarios.owner.domain.exceptions;

public class OwnerAlreadyExistsException extends RuntimeException {
    public OwnerAlreadyExistsException(String message){
        super(message);
    }

}
