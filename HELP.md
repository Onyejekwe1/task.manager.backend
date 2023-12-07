# Task Manager Application
This project is a simple Task Manager web application built with NuxtJS (Version 2) for the frontend and Spring Boot Webflux for the backend. 
The application allows users to view, create, and mark tasks as completed.

### Getting Started
Follow these instructions to get the project up and running on your local machine for development and testing purposes.

### Prerequisites
You need to have the following installed:

* Java JDK 17
* Maven (for building and running the backend)
* Docker (optional, for containerization)
* Node.js and npm (for running the frontend)

### Backend - Spring Boot
Running with Maven

1. Clone the repository and navigate to the backend directory:

```shell
git clone https://your-repository-url.git
```
```shell
cd your-project-backend-directory
```

2. Build the project using Maven:

```shell
mvn clean install
```
3. Run the application:

```shell
mvn spring-boot:run
```
The backend should now be running on http://localhost:8080.

### Running with Docker

1. Build the Docker image:
```shell
docker build -t taskmanager-backend .
```

2. Run the Docker container:

```shell
docker run -p 8080:8080 taskmanager-backend
```

Access the application at http://localhost:8080.

### Running Tests
Run the automated tests for the backend:

```shell
mvn test
```

## Usage
* The backend API provides endpoints for managing tasks (creating, viewing, and updating).
* The frontend provides a user interface for interacting with these tasks.