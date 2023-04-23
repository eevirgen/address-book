package com.addressbook.infrastructure.datasource;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
class FilePersonDataSourceTest {

    @Test
    void shouldReadValidFile() throws IOException {
        // Initialize FilePersonDataSource with the temporary file path
        FilePersonDataSource filePersonDataSource = new FilePersonDataSource("TestAddressBook");

        // Read the temporary file using FilePersonDataSource
        List<String> lines = filePersonDataSource.read();

        // Check the size of the returned list and the details of the first person
        assertEquals(2, lines.size());
        assertEquals("John Doe, Male, 01/01/1990", lines.get(0));
        assertEquals("Jane Doe, Female, 02/02/1995", lines.get(1));
    }

    @Test
    void shouldReturnEmptyList_whenFileIsEmpty() throws IOException {
        // Initialize FilePersonDataSource with the temporary file path
        FilePersonDataSource filePersonDataSource = new FilePersonDataSource("TestAddressBook_Empty");

        // Read the temporary file using FilePersonDataSource
        List<String> lines = filePersonDataSource.read();

        // Check that the returned list is empty
        assertEquals(0, lines.size());
    }
}
