package com.addressbook;

import com.addressbook.domain.repository.AddressBook;
import com.addressbook.infrastructure.datasource.FilePersonDataSource;
import com.addressbook.infrastructure.datasource.PersonDataSource;
import com.addressbook.infrastructure.mapper.PersonMapper;
import com.addressbook.infrastructure.reader.AddressBookReader;

import java.io.IOException;
import java.util.Optional;

public class AddressBookApplication {
    public static void main(String[] args) throws IOException {
        PersonMapper personMapper = new PersonMapper();
        PersonDataSource personDataSource = new FilePersonDataSource("AddressBook");
        AddressBookReader reader = new AddressBookReader(personDataSource, personMapper);
        AddressBook addressBook = reader.readAddressBook();

        System.out.println("Number of males: " + addressBook.countMales());
        System.out.println("Oldest person: " + addressBook.findTheOldestPerson().name());
        Optional<Long> daysDifference = addressBook.daysDifferenceBetweenTwoPeople("Bill McKnight", "Paul Robinson");
        daysDifference.ifPresentOrElse(
                difference -> System.out.println("Bill is " + difference + " days older than Paul."),
                () -> System.out.println("One or both of the names were not found in the address book.")
        );
    }
}
