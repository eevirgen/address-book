package com.addressbook.domain.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
class GenderTest {
    @Test
    void fromString_shouldReturnCorrectGender_whenMale() {
        assertEquals("MALE", Gender.MALE.name());
    }
    @Test
    void fromString_shouldReturnCorrectGender_whenFemale() {
        assertEquals("FEMALE", Gender.FEMALE.name());
    }
}
