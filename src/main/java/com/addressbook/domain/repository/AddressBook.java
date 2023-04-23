package com.addressbook.domain.repository;

import com.addressbook.domain.model.Gender;
import com.addressbook.domain.model.Person;
import com.addressbook.domain.exception.InvalidPersonException;

import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

/**
 * Represents an address book containing a list of people.
 * <p>
 * Provides methods to perform required operations on the list:
 * counting males, finding the oldest person and calculating the age difference between two people.
 * @author erk
 */

public class AddressBook {

    private final List<Person> people;

    public AddressBook(List<Person> people) {
        this.people = people;
    }

    public long countMales() {
        return people.stream().filter(person -> person.getGender() == Gender.MALE).count();
    }

    public Person findTheOldestPerson() {
        return people.stream().min(Comparator.comparing(Person::getDateOfBirth)).orElse(null);
    }

    public Optional<Long> daysDifferenceBetweenTwoPeople(String name1, String name2) {

        Optional<Person> person1 = Optional.ofNullable(findPersonByName(name1));
        Optional<Person> person2 = Optional.ofNullable(findPersonByName(name2));

        if (person1.isEmpty() || person2.isEmpty()) {
            throw new InvalidPersonException("One or both names not found in the address book.");
        }

        return Optional.of(ChronoUnit.DAYS.between(person1.get().getDateOfBirth(), person2.get().getDateOfBirth()));
    }

    // Finds a person in the address book by their name (case-insensitive)
    private Person findPersonByName(String name) {
        return people.stream().filter(person -> person.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public List<Person> getPeople() {
        return people;
    }

}
