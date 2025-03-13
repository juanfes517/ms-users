package com.pragma.user.domain.exception;

public class InvalidCellPhoneNumberException extends RuntimeException{

    public InvalidCellPhoneNumberException(String message) {
        super(message);
    }
}
