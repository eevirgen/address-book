package com.addressbook.integration;

import com.addressbook.domain.repository.AddressBook;
import com.addressbook.domain.exception.InvalidPersonDataException;
import com.addressbook.infrastructure.datasource.FilePersonDataSource;
import com.addressbook.infrastructure.datasource.PersonDataSource;
import com.addressbook.infrastructure.mapper.PersonMapper;
import com.addressbook.infrastructure.reader.AddressBookReader;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class AddressBookApplicationIntegrationTest {

    @Test
    void shouldReadAddressBookAndAnswerQuestions() throws IOException {
        PersonMapper personMapper = new PersonMapper();
        PersonDataSource personDataSource = new FilePersonDataSource("AddressBook");
        AddressBookReader reader = new AddressBookReader(personDataSource, personMapper);
        AddressBook addressBook = reader.readAddressBook();

        // Number of males: 3
        assertEquals(3, addressBook.countMales());

        // Oldest person: Wes Jackson
        assertEquals("Wes Jackson", addressBook.findTheOldestPerson().name());

        // Bill is 2862 days older than Paul.
        Optional<Long> daysDifference = addressBook.daysDifferenceBetweenTwoPeople("Bill McKnight", "Paul Robinson");
        assertTrue(daysDifference.isPresent());
        assertEquals(2862, daysDifference.get());
    }

    @Test
    void shouldReadAddressBookAndThrowInvalidPersonDataException() {
        PersonMapper personMapper = new PersonMapper();
        PersonDataSource personDataSource = new FilePersonDataSource("TestAddressBook_InvalidData");
        AddressBookReader reader = new AddressBookReader(personDataSource, personMapper);
        assertThrows(InvalidPersonDataException.class, reader::readAddressBook);
    }
}
