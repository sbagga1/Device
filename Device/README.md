**Vodafone Coding Exercise**

This is a RESTful web service built with Spring Boot that allows users to manage a collection of devices. This application provides endpoints to perform CRUD (Create, Read, Update, Delete) operations on devices, making it easy to integrate with other systems or applications that require device management functionality.

## Technologies Used

- **Java 17**: The latest LTS version of Java.
- **Spring Boot**: Framework for building the RESTful API.
- **H2 Database**: In-memory database for development and testing.
- **Maven**: Dependency management and build tool.
- **JUnit 5**: Testing framework for unit and integration tests.

## Getting Started

Follow the instructions below to set up and run the application:

1. Build the code base `mvn clean install`
2. Run via Maven using Boot: `mvn spring-boot:run`
3. The application can also be run via Docker though this is not required, simply:
`docker build -t device-api . && docker run -p 8080:8080 --name vodafone-challenge device-api`

Once the API is running, it can be consumed:

`GET localhost:8080/devices/1` - to get a specific device

`POST localhost:8080/devices` - POST to add a new device to embedded DB.

All tests will be executed as part of build but can also be run independently if need be.

**Tasks:**

You will be provided the tasks at the start of the interview.
