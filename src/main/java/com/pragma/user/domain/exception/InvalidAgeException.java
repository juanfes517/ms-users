package com.pragma.user.domain.exception;

public class InvalidAgeException extends RuntimeException {

    public InvalidAgeException(String message) {
        super(message);
    }
}
