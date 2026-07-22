# Library Management System

This is a REST API project developed with Spring Boot for managing a library system.

The project allows creating, updating, deleting and viewing authors, books and members. It also supports borrowing and returning books.

## Technologies

- Java 21
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Gradle
- Liquibase
- Lombok
- Swagger / OpenAPI
- Groovy & Spock

## Project Structure

The project follows a layered architecture:

- Controller
- Service
- Repository
- Entity
- DTO
- Mapper

Entity objects are not returned directly. DTO classes are used for requests and responses.

## Features

- Author CRUD
- Book CRUD
- Member CRUD
- Pagination & Sorting
- Input Validation
- Global Exception Handling
- Swagger Documentation
- Unit Tests

## How to Run

### 1. Clone the repository

```bash
git clone https://github.com/your-username/library-management-system.git
```

### 2. Create PostgreSQL database

Example:

```
library_management_system
```

### 3. Configure application.yml

Update your database credentials.

Example:

```yaml
spring:
  datasource:
    url: jdbc:postgresql://localhost:5432/library_management_system
    username: your_username
    password: your_password
```

### 4. Run the project

Run the main Spring Boot application.

Liquibase will automatically create the database tables.

## Swagger

After starting the application, open:

```
http://localhost:1111/swagger-ui/index.html
```

## Validation

Validation is implemented using annotations such as:

- @NotBlank
- @NotNull
- @Size
- @Positive
- @Pattern
- @Email

## Exception Handling

Global exception handling is implemented using `@RestControllerAdvice`.

Custom exceptions:

- AuthorNotFoundException
- BookNotFoundException
- MemberNotFoundException
- BookAlreadyBorrowedException
- BookAlreadyReturnedException

## Testing

Unit tests were written for the service layer using Groovy and Spock Framework.