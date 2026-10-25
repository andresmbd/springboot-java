package com.myproject.spring_practica.domain.exception;

public class InvalidEnterDataException extends RuntimeException {
    public InvalidEnterDataException(String message) {
        super(message);
    }
}
