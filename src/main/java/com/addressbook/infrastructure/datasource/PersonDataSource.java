package com.addressbook.infrastructure.datasource;

import java.io.IOException;
import java.util.List;

public interface PersonDataSource {
    List<String> read() throws IOException;
}

