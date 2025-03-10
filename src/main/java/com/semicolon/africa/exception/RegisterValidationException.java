package com.semicolon.africa.exception;

public class RegisterValidationException extends RuntimeException {
    public RegisterValidationException(String message) {
        super(message);
    }
}
