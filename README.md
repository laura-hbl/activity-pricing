# Activity Pricing System

This project calculates activity prices based on the type of activity, the day of the week, and the number of participants.

## Prerequisites

Before running the project, ensure you have the following installed:

- **Java 21 JDK**
- **Maven 4.0.0**

## Project structure

The project is composed of two Maven modules:

- Domain: Contains the core business logic, including entities, use cases, and domain services.
- Adapters: Implements a Spring Boot application. Includes a controller to expose a REST API and manage incoming HTTP request.

This design ensures the domain logic is decoupled from application concerns.

## Technical Specifications

The system is built using the following technologies:

- **Java 21**: Backend logic and application.
- **Spring Boot**: Framework for building the application.
- **Maven**: Dependency and build management.

## Running the application Locally

To run the application on your local machine, follow these steps:

1. **Clone the repository**:
   ```bash
   git clone https://github.com/laura-hbl/activity-pricing.git
   ```
2. **Build the project**: Navigate to the project's root directory:
   ```bash
   cd activitypricing
   ```
   and run:
   ```bash
   mvn clean install
   ```
3. **Navigate to the adapters module**:
   ```bash
   cd adapters
   ```
4. **Run the Application**:
   ```bash
   mvn spring-boot:run
   ```
5. The application will be accessible at: `http://localhost:8080`

## API Endpoints

1. **Calculate total price for an activity**

    - **Method**: `POST`
    - **Endpoint**: `http://localhost:8080/activities/price`
    - **Description**: Calculates the total price for a specific activity type based on the provided day of the week and the number of participants.
      - **Request Body**:
        ```json
        {
        "day": "MONDAY",
        "activityType": "FREE_SALE",
        "numberOfAdults": 2,
        "numberOfChildren": 1
        }
      
    - **Response Body**:
      ```json
        {
        "price": 50.0
        }
      ```

## Running Tests

The project includes unit tests. To execute them, run:

1. **Navigate to the domain module**:
   ```bash
   cd domain
   ```

2. **Run Tests**
   ```bash
   mvn test
   ```
