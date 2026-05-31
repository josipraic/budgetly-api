# Budgetly API

Budgetly API is a RESTful backend application for personal expense tracking built with Java 21 and Spring Boot.

## Features

* User registration and login
* JWT authentication and authorization
* Secure password hashing with BCrypt
* Category management
* Transaction management
* User-specific data access
* Protected REST endpoints

## Tech Stack

* Java 21
* Spring Boot 3
* Spring Security
* JWT
* Spring Data JPA
* H2 Database
* Maven

## API Endpoints

### Authentication

* POST `/api/auth/register`
* POST `/api/auth/login`

### Categories

* POST `/api/categories`
* GET `/api/categories`
* DELETE `/api/categories/{id}`

### Transactions

* POST `/api/transactions`
* GET `/api/transactions`
* GET `/api/transactions?type=EXPENSE`
* DELETE `/api/transactions/{id}`

## Security

The application uses JWT (JSON Web Tokens) for authentication. Protected endpoints require a valid Bearer token in the Authorization header.

Example:

Authorization: Bearer your-jwt-token

## Run Locally

```bash
git clone <repository-url>
cd budgetly-api
./mvnw spring-boot:run
```

The application runs on:

http://localhost:9091
