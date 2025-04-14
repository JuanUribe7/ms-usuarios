package com.example.plazoleta.ms_usuarios.owner.infrastructure.exceptionshandler;

import com.example.plazoleta.ms_usuarios.owner.domain.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;



@ControllerAdvice
public class ControllerAdvisor {
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.FIELD_VALIDATION_ERROR),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidEmailException(InvalidEmailException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.INVALID_EMAIL),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidPhoneException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidPhoneException(InvalidPhoneException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.INVALID_PHONE),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(InvalidIdentityDocumentException.class)
    public ResponseEntity<ExceptionResponse> handleInvalidIdentityDocumentException(InvalidIdentityDocumentException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.INVALID_DOCUMENT),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(MinorAgeException.class)
    public ResponseEntity<ExceptionResponse> handleMinorAgeException(MinorAgeException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.MINOR_AGE),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(OwnerAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleOwnerAlreadyExistsException(OwnerAlreadyExistsException ex) {
        return new ResponseEntity<>(
                new ExceptionResponse(ex.getMessage(), ExceptionConstants.OWNER_ALREADY_EXISTS),
                HttpStatus.CONFLICT
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage())
        );
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}