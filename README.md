# address-book

### tech stack
- Java 17
- Gradle
- Junit
- Mockito

### how to build, run an test 

to build :`./gradlew build`

to run the tests : `./gradlew test`

to run the main app : `./gradlew run`

### code

The main objectives of this project are to count the number of males, find the oldest person, and calculate the difference in days between two people's birthdates.

#### assumptions
- The input data is in a specific format: Name, Gender, Date of Birth (dd/MM/yy).
- The data file is provided as a resource in the project.

#### intentions
- Implement a modular and maintainable codebase by following Domain Driven Design principles.
- Ensure the robustness and reliability of the application by adhering to Test Driven Development practices.
- Handle invalid data entries gracefully by using custom exceptions.
- Leverage Java's functional programming features to write concise and expressive code.
- Provide a well-documented and easy-to-understand project structure for future development and maintenance.

#### architecture

**Domain Layer**: Contains the core domain concepts, such as Person, Gender, and AddressBook. This layer is responsible for defining the business rules and domain logic.

**Infrastructure Layer**: Provides implementations for domain interfaces, such as the PersonDataSource for reading person data and the PersonMapper for converting raw data strings into Person objects.

**Application Layer**: Coordinates the interactions between the domain and infrastructure layers, such as reading the address book data, creating an AddressBook object, and performing the required operations.

#### design patterns

- **Domain Driven Design (DDD):** Separates domain logic from infrastructure and application layers for modularity and maintainability.
- **Repository Pattern:** Abstracts access to the collection of Person objects, allowing easy querying and manipulation.
- **Dependency Inversion Principle (DIP):** High-level AddressBookReader depends on the PersonDataSource interface, not on the low-level implementation, promoting flexibility and easier testing.
- **Data Mapper Pattern:** PersonMapper converts raw data into Person objects, isolating domain logic from data access and transformation.
