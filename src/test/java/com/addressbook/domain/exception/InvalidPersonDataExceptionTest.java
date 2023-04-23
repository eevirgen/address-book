package com.addressbook.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class InvalidPersonDataExceptionTest {
    @Test
    void shouldConstructAndReturnMessage() {
        String expectedMessage = "Invalid person data.";
        InvalidPersonDataException exception = new InvalidPersonDataException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage());
    }
}
