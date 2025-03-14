package com.pragma.user.domain.exception;

public class InvalidDocumentIdException extends RuntimeException{
    public InvalidDocumentIdException(String message) {
        super(message);
    }
}
