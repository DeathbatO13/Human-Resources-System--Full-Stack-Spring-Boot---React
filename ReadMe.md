
# Human Resources System - Full Stack Spring Boot + React

A complete employee management platform featuring CRUD operations, a responsive dark UI, and clean professional documentation. Designed for clarity, performance, and a smooth developer experience.

## 🚀Features

- **Dockerized Environment:** Single-command setup for Backend, Frontend, and Database.

- **Robust Testing:** Backend covered with JUnit 5 and Mockito.

- **Employee management:** (Create, Read, Update, Delete).

- **Salary formatting:** with currency support.

- **Responsive dark-themed interface:** Built with Bootstrap 5.

- **Clean API structure:** Using Spring Boot 3.3+.

## 🛠Tech Stack

### Backend
- Java 17
- Spring Boot 3.3+
- Spring Data JPA
- MySQL Database

### Frontend
- React 18 + Vite
- React Router v6
- Axios
- Bootstrap 5 + custom CSS

## 📸Screenshots

### Employee List
![Employee List](img/Dash.png)

### Add Employee
![Add Employee](img/Add.png)

### Edit Employee
![Edit Employee](img/Edit.png)


## 🧱 Architecture Overview

The project follows a clean full-stack architecture, separating frontend UI components from backend business logic.
The backend exposes REST endpoints, and the frontend interacts with them through Axios.

### 📂 Backend Architecture (Spring Boot)

    src/main/java/gm/rh
    │
    ├── controlador/              # REST controllers
    │   └── EmpleadoControlador
    │
    ├── excepcion/                # Custom exception handling
    │   └── RecursoNoEncontradoExcepcion
    │
    ├── modelo/                   # JPA entities
    │   └── Empleado
    │
    ├── repository/               # Spring Data JPA repositories
    │   └── EmpleadoRepository
    │
    ├── service/                  # Business logic layer
    │   ├── EmpleadoServicio
    │   └── IEmpleadoServicio
    │
    └── RhApplication             # Main Spring Boot application

### 🔎 Backend Flow 
    
    Controller → Service → Repository → MySQL Database

### 📂 Frontend Architecture (React + Vite)

    src/
    │
    ├── empleados/                # CRUD views
    │   ├── AgregarEmpleado.js
    │   ├── EditarEmpleado.js
    │   └── ListadoEmpleados.js
    │
    ├── plantilla/                # Reusable layout components
    │   └── Navegacion.js
    │
    ├── App.js                    # Main router
    ├── index.js                  # Entry point
    └── index.css                 # Global styles

### 🔎 Frontend Flow

    React Router → UI Components → Axios → REST API

## 📊 System Architecture Diagram

               ┌────────────────────────┐
               │        React App       │
               │  (Axios, React Router) │
               └─────────────┬──────────┘
                             │
                             ▼
               ┌────────────────────────┐
               │     Spring Boot API    │
               │ Controller | Service   │
               │     Repository Layer   │
               └─────────────┬──────────┘
                             │
                             ▼
               ┌────────────────────────┐
               │     MySQL Database     │
               └────────────────────────┘


##  ▶ How to run

### Backend

```bash
cd Spring
./mvnw spring-boot:run
```

API: http://localhost:8080/rh-app

## Frontend

```bash
cd react
npm install
npm run dev
```

App: http://localhost:3000

## 🐳 Docker Deployment (Recommended)

To get the entire system up and running in seconds, ensure you have Docker and Docker Compose installed.

Bash

### Clone the repository

    git clone https://github.com/tu-usuario/tu-repo.git
    cd tu-repo

### Build and run the containers

    docker-compose up --build
    Frontend: http://localhost:3000

Backend API: localhost:8080/rh-app

Database: localhost:3306

## 🧪 Testing

### Backend (Unit Testing)

The business logic is protected by a suite of tests using JUnit 5 and Mockito, ensuring the service layer and controllers behave as expected.

Bash

    cd Spring
    ./mvnw test

[!TIP] Tests cover: Service layer logic, Repository interactions (mocked), and Custom Exception handling.

## 🚧 Future Improvements (Roadmap)

### Backend

- Add pagination for /empleados

- Add query filters (salary, last name, etc.)

- Implement authentication (JWT or Basic Auth)

- Integrate Swagger/OpenAPI documentation

### Frontend

- Add delete confirmation modals

- Use Formik + Yup for advanced validation

- Add global loading indicators

- Integrate React Query for data caching

- Add theme switcher (Dark/Light mode)

### DevOps

- CI/CD using GitHub Actions


## 👤 Author

Made with passion by Daniel Torres
If you like this project, consider giving it a ⭐ on GitHub.