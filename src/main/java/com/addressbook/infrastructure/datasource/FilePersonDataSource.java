package com.addressbook.infrastructure.datasource;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * A file-based implementation of the PersonDataSource interface.
 * Reads person data from a resource file and returns it as a list of strings.
 * @author erk
 */
public class FilePersonDataSource implements PersonDataSource {
    private final InputStream inputStream;

    /**
     * Constructs a FilePersonDataSource with the given resource name.
     *
     * @param resource The name of the resource file containing person data.
     */
    public FilePersonDataSource(String resource) {
        this.inputStream = this.getClass().getClassLoader().getResourceAsStream(resource);
    }

    /**
     * Reads person data from the resource file and returns it as a list of strings.
     *
     * @return A list of strings, each representing a person's data from the file.
     * @throws IOException If an I/O error occurs while reading the file.
     */
    @Override
    public List<String> read() throws IOException {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        }

        return lines;
    }
}
