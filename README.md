# Affenbande Backend

REST backend for a sports and exercise platform, developed with Kotlin and Spring Boot.
## Features

* **Move Management:**  CRUD operations for managing exercise moves, including details like name, description, difficulty level, intensity, associated sports, and pre-requisite moves.
* **Data Relationships:**  Supports relationships between moves, subcategories, and sports, allowing for complex data querying and filtering.
* **Image Handling:**  Handles uploading and associating images with moves.
* **Scalable Architecture:**  Built with Spring Boot for robust performance and scalability.
* **Clear Documentation:**  Provides comprehensive API documentation through Swagger UI.

## Tech Stack

* **Language:** Kotlin
* **Framework:** Spring Boot
* **Database:** PostgreSQL
* **API Documentation:** Swagger UI
* **Image Storage:** local storage

## Architecture

The backend follows a layered architecture pattern, separating concerns into distinct layers:

* **Controller Layer:** Handles incoming HTTP requests and responses, mapping API endpoints to service methods.
* **Service Layer:** Contains the business logic, orchestrating operations between the controller and repository layers.
* **Repository Layer:** Manages data access and persistence, interacting with the PostgreSQL database.
* **Model Layer:** Defines the data structures and entities used throughout the application.
* **Configuration Layer:** Manages application settings, security, and other configuration aspects.

Flutter App -> REST API - Spring Boot - JPA - PostgreSQL