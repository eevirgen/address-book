package com.addressbook.infrastructure.reader;

import com.addressbook.domain.model.Gender;
import com.addressbook.domain.model.Person;
import com.addressbook.domain.repository.AddressBook;
import com.addressbook.infrastructure.datasource.PersonDataSource;
import com.addressbook.infrastructure.mapper.PersonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class AddressBookReaderTest {

    private PersonDataSource personDataSource;
    private PersonMapper personMapper;
    private AddressBookReader addressBookReader;

    @BeforeEach
    void setUp() {
        personDataSource = mock(PersonDataSource.class);
        personMapper = mock(PersonMapper.class);
        addressBookReader = new AddressBookReader(personDataSource, personMapper);
    }

    @Test
    void shouldReadValidAddressBook() throws IOException {
        List<String> lines = Arrays.asList("Line 1", "Line 2");
        Person person1 = new Person("John Doe", Gender.MALE, LocalDate.of(1990, 1, 1));
        Person person2 = new Person("Jane Doe", Gender.FEMALE, LocalDate.of(1995, 2, 2));

        when(personDataSource.read()).thenReturn(lines);
        when(personMapper.map("Line 1")).thenReturn(person1);
        when(personMapper.map("Line 2")).thenReturn(person2);

        AddressBook addressBook = addressBookReader.readAddressBook();

        assertEquals(2, addressBook.getPeople().size());
        assertEquals(person1, addressBook.getPeople().get(0));
        assertEquals(person2, addressBook.getPeople().get(1));

        verify(personDataSource, times(1)).read();
        verify(personMapper, times(1)).map("Line 1");
        verify(personMapper, times(1)).map("Line 2");
    }

    @Test
    void shouldHandleEmptyAddressBook() throws IOException {
        List<String> lines = Collections.emptyList();

        when(personDataSource.read()).thenReturn(lines);

        AddressBook addressBook = addressBookReader.readAddressBook();

        assertEquals(0, addressBook.getPeople().size());

        verify(personDataSource, times(1)).read();
        verifyNoInteractions(personMapper);
    }
}
