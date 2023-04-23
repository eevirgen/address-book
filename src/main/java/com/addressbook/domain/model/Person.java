package com.addressbook.domain.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.Optional;

/**
 * Represents a person with a name, gender, and date of birth.
 * <p>
 * Provides a method to calculate the number of days difference between the age of this person and another person.
 *
 * @author erk
 */
public record Person(String name, Gender gender, LocalDate dateOfBirth) {
    /**
     * Calculates the number of days difference between the ages of this person and another person.
     *
     * @param other The other person to compare ages with.
     * @return An Optional containing the number of days difference if the other person is not null, otherwise an empty Optional.
     */
    public Optional<Long> daysDifference(Person other) {
        if (other == null) {
            return Optional.empty();
        }
        return Optional.of(ChronoUnit.DAYS.between(this.dateOfBirth, other.dateOfBirth));
    }
}
