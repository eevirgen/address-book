package com.addressbook.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidPersonExceptionTest {
    @Test
    void shouldConstructAndReturnMessage() {
        String expectedMessage = "Invalid person.";
        InvalidPersonException exception = new InvalidPersonException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
