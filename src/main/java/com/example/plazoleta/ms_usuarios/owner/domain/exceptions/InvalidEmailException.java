package com.example.plazoleta.ms_usuarios.owner.domain.exceptions;

public class InvalidEmailException extends RuntimeException{
    public InvalidEmailException(String message){
        super(message);
    }
}
