package com.addressbook.infrastructure.mapper;

import com.addressbook.domain.model.Person;
import com.addressbook.domain.model.Gender;
import com.addressbook.domain.exception.InvalidPersonDataException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Optional;

/**
 * Maps a string containing person data to a Person object.
 * Parses the name, gender, and date of birth from the input string.
 *
 * @author erk
 */
public class PersonMapper {

    /**
     * Maps a string containing person data to a Person object.
     *
     * @param line A string containing the name, gender, and date of birth of a person.
     * @return A Person object created from the input string.
     * @throws InvalidPersonDataException If the input string is not properly formatted.
     */
    public Person map(String line) {
        String[] parts = line.split(", ");

        if (parts.length != 3) {
            throw new InvalidPersonDataException("Invalid person data: " + line);
        }

        String name = parts[0];
        Gender gender = findGender(parts[1]).orElseThrow(() -> new InvalidPersonDataException("Invalid gender: " + parts[1]));

        LocalDate birthdate = parseDate(parts[2])
                .orElseThrow(() -> new InvalidPersonDataException("Invalid birthdate: " + parts[2]));

        return new Person(name, gender, birthdate);
    }

    /**
     * Finds the Gender enum value corresponding to the input string.
     *
     * @param genderString A string representing the gender.
     * @return An Optional containing the matching Gender enum value, or an empty Optional if no match is found.
     */
    private Optional<Gender> findGender(String genderString) {
        return Arrays.stream(Gender.values())
                .filter(gender -> gender.name().equalsIgnoreCase(genderString))
                .findFirst();
    }

    /**
     * Parses a date string to a LocalDate object.
     *
     * @param dateString A string representing the date in the format "dd/MM/yy".
     * @return An Optional containing the parsed LocalDate, or an empty Optional if the input string is not properly formatted.
     */
    private Optional<LocalDate> parseDate(String dateString) {
        try {
            LocalDate dob = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("dd/MM/yy"));
            return Optional.of(dob.isAfter(LocalDate.now()) ? dob.minusYears(100) : dob);
        } catch (DateTimeParseException e) {
            return Optional.empty();
        }
    }

}
