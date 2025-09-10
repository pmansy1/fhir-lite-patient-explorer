# FHIR Lite Patient Explorer

A lightweight backend service for exploring patient data inspired by the **HL7 FHIR** standard.  
Built with **Spring Boot**, **Spring Data JPA**, and **PostgreSQL**, containerized using **Docker** for easy setup and deployment.  

---

## Features

- RESTful API for patient data (basic CRUD and search endpoints)  
- Pre-seeded with synthetic patient data (`schema.sql` + `data.sql`)  
- Repository, domain, and service layers with validation  
- API documentation enabled via **Swagger UI**  
- Ready for integration with a React-based frontend  

---

## Tech Stack

- **Java 21**  
- **Spring Boot 3** (Web, Data JPA, Validation)  
- **PostgreSQL** (with seed scripts)  
- **Docker & Docker Compose**  
- **Maven** for build and dependency management  

---

## Getting Started

### Prerequisites
- [Docker](https://www.docker.com/) installed  
- [Java 21](https://jdk.java.net/21/) + [Maven](https://maven.apache.org/) (if running locally without Docker)

### Run with Docker

```bash
# Build the application
./mvnw clean package -DskipTests

# Start the app and database
docker compose up --build
