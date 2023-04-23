package com.addressbook.infrastructure.mapper;

import com.addressbook.domain.model.Gender;
import com.addressbook.domain.model.Person;
import com.addressbook.domain.exception.InvalidPersonDataException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PersonMapperTest {

    @Test
    void shouldMapValidPersonString() {
        String validPersonString = "John Doe, Male, 01/01/90";
        PersonMapper personMapper = new PersonMapper();
        Person person = personMapper.map(validPersonString);

        assertEquals("John Doe", person.getName());
        assertEquals(Gender.MALE, person.getGender());
        assertEquals(1990, person.getDateOfBirth().getYear());
    }

    @Test
    void shouldThrowIllegalArgumentException_whenInvalidPersonString() {
        String invalidPersonString = "Invalid Person String";
        PersonMapper personMapper = new PersonMapper();

        assertThrows(InvalidPersonDataException.class, () -> personMapper.map(invalidPersonString));
    }

    @Test
    void shouldThrowInvalidPersonDataException_whenInvalidDate() {
        String invalidDatePersonString = "John Doe, Male, 01/30/90";
        PersonMapper personMapper = new PersonMapper();

        assertThrows(InvalidPersonDataException.class, () -> personMapper.map(invalidDatePersonString));
    }

    @Test
    void shouldThrowInvalidPersonDataException_whenInvalidGender() {
        String invalidGenderPersonString = "John Doe, XCXC, 01/01/90";
        PersonMapper personMapper = new PersonMapper();

        assertThrows(InvalidPersonDataException.class, () -> personMapper.map(invalidGenderPersonString));
    }
}
