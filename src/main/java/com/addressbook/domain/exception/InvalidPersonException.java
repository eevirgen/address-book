package com.addressbook.domain.exception;

public class InvalidPersonException extends RuntimeException {
    public InvalidPersonException(String message) {
        super(message);
    }
}
