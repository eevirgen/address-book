package com.addressbook.domain.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {
    @Test
    void daysDifference_shouldReturnCorrectDifference() {
        Person person1 = new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16));
        Person person2 = new Person("Paul Robinson", Gender.MALE, LocalDate.of(1985, 1, 15));

        Optional<Long> daysDifference = person1.daysDifference(person2);
        assertTrue(daysDifference.isPresent());
        assertEquals(2862, daysDifference.get());
    }

    @Test
    void daysDifference_shouldReturnEmpty_whenOtherIsNull() {
        Person person1 = new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16));

        Optional<Long> daysDifference = person1.daysDifference(null);
        assertFalse(daysDifference.isPresent());
    }
}
