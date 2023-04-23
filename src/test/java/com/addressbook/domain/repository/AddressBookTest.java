package com.addressbook.domain.repository;
import com.addressbook.domain.model.Gender;
import com.addressbook.domain.model.Person;
import com.addressbook.domain.exception.InvalidPersonException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class AddressBookTest {
    private AddressBook addressBook;
    private Person bill;

    @BeforeEach
    void setUp() {
        bill = new Person("Bill McKnight", Gender.MALE, LocalDate.of(1977, 3, 16));
        Person paul = new Person("Paul Robinson", Gender.MALE, LocalDate.of(1985, 1, 15));
        List<Person> people = Arrays.asList(bill, paul);
        addressBook = new AddressBook(people);
    }

    @Test
    void countMales_shouldReturnCorrectCount() {
        assertEquals(2, addressBook.countMales());
    }

    @Test
    void findOldestPerson_shouldReturnCorrectOldestPerson() {
        assertEquals(bill, addressBook.findTheOldestPerson());
    }

    @Test
    void daysDifferenceBetweenTwoPeople_shouldReturnCorrectDifference() {
        Optional<Long> daysDifference = addressBook.daysDifferenceBetweenTwoPeople("Bill McKnight", "Paul Robinson");
        assertTrue(daysDifference.isPresent());
        assertEquals(2862, daysDifference.get());
    }

    @Test
    void daysDifferenceBetweenTwoPeople_shouldThrowInvalidPersonException_whenOneOrBothNamesNotFound() {
        assertThrows(InvalidPersonException.class, () -> addressBook.daysDifferenceBetweenTwoPeople("Nonexistent Person", "Paul Robinson"));
    }
}
