package com.addressbook.domain.exception;
public class InvalidPersonDataException extends RuntimeException {
    public InvalidPersonDataException(String message) {
        super(message);
    }
}
