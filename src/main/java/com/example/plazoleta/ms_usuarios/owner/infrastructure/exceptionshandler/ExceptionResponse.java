package com.example.plazoleta.ms_usuarios.owner.infrastructure.exceptionshandler;

public class ExceptionResponse {
    private final String message;
    private final String code;

    public ExceptionResponse(String message, String code) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
