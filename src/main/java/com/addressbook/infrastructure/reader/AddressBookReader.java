package com.addressbook.infrastructure.reader;

import com.addressbook.domain.model.Person;
import com.addressbook.domain.repository.AddressBook;
import com.addressbook.infrastructure.datasource.PersonDataSource;
import com.addressbook.infrastructure.mapper.PersonMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads an address book from a data source and creates an {@link AddressBook} object.
 *
 * @author erk
 */
public class AddressBookReader {

    private final PersonDataSource personDataSource;
    private final PersonMapper personMapper;

    /**
     * Constructs an AddressBookReader with the specified data source and mapper.
     *
     * @param personDataSource The data source to read person data from.
     * @param personMapper The mapper to convert person data strings to Person objects.
     */
    public AddressBookReader(PersonDataSource personDataSource, PersonMapper personMapper) {
        this.personDataSource = personDataSource;
        this.personMapper = personMapper;
    }

    /**
     * Reads the address book data from the data source and creates an AddressBook object.
     *
     * @return An AddressBook object containing the read person data.
     * @throws IOException If there is an issue reading from the data source.
     */
    public AddressBook readAddressBook() throws IOException {
        List<String> lines = personDataSource.read();
        List<Person> people = new ArrayList<>();

        for (String line : lines) {
            Person person = personMapper.map(line);
            people.add(person);
        }

        return new AddressBook(people);
    }
}

