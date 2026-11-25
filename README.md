
# RP Bank – Enterprise Java Full Stack Banking Portal

A full-stack banking portal built using Java 17, Spring Boot, Spring Security, JWT authentication, Spring Data JPA, MySQL, and React.

## Features

### Authentication and Security
- User registration and login
- BCrypt password encryption
- JWT-based authentication
- Stateless session management
- Protected REST API endpoints

### Banking
- Create a bank account
- View account details
- Check account balance
- Deposit money
- Validate deposit amounts

### Backend
- RESTful API architecture
- Spring Data JPA and Hibernate
- MySQL database integration
- Global exception handling
- Request validation
- Swagger / OpenAPI documentation

### Frontend
- React and Vite
- Axios API integration
- Login and dashboard
- Account balance display
- Deposit functionality
- JWT token-based API requests

## Technology Stack

| Layer | Technologies |
|---|---|
| Backend | Java 17, Spring Boot 3.5.16 |
| Security | Spring Security 6, JWT |
| Database | MySQL 8 |
| ORM | Spring Data JPA, Hibernate |
| Frontend | React, Vite |
| API Client | Axios |
| Documentation | Swagger / OpenAPI |
| Build Tool | Maven |
| API Testing | Postman |

## Project Structure

```text
spring-boot-rest-api/
├── frontend/
│   └── src/
│       ├── App.jsx
│       ├── App.css
│       └── main.jsx
├── src/
│   └── main/
│       ├── java/com/rabtech/api/
│       │   ├── config/
│       │   ├── controller/
│       │   ├── dto/
│       │   ├── entity/
│       │   ├── exception/
│       │   ├── repository/
│       │   └── service/
│       └── resources/
├── pom.xml
└── README.md
```

## Prerequisites

- Java 17 or later
- MySQL 8
- Node.js and npm
- Git

## Backend Setup

1. Clone the repository:

```bash
git clone https://github.com/Ramapradheesh/spring-boot-rest-api.git
cd spring-boot-rest-api
```

2. Create a MySQL database:

```sql
CREATE DATABASE employee_management;
```

3. Configure environment variables.

PowerShell example:

```powershell
$env:DB_URL="jdbc:mysql://localhost:3306/employee_management"
$env:DB_USERNAME="root"
$env:DB_PASSWORD="YOUR_MYSQL_PASSWORD"
$env:JWT_SECRET="YOUR_BASE64_JWT_SECRET"
```

Use your own credentials. Never commit secrets to GitHub.

4. Run the backend:

```powershell
.\mvnw.cmd spring-boot:run
```

Backend URL:

http://localhost:8080

## Frontend Setup

Open another terminal:

```powershell
cd frontend
npm install
npm run dev
```

Frontend URL:

http://localhost:5173

## API Endpoints

### Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register user |
| POST | `/api/auth/login` | Login user |

### Bank Account

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/accounts` | Create account |
| GET | `/api/accounts/me` | View account |
| POST | `/api/accounts/deposit` | Deposit money |

Protected endpoints require:

```text
Authorization: Bearer <JWT_TOKEN>
```

## API Documentation

Swagger UI:

http://localhost:8080/swagger-ui/index.html

OpenAPI specification:

http://localhost:8080/v3/api-docs

## Testing

- Postman used for REST API testing.
- Swagger UI used for API documentation and endpoint testing.
- Maven used for backend compilation.

## Security Notes

- Passwords are encrypted using BCrypt.
- JWT tokens are used for authentication.
- Protected endpoints require valid authentication.
- Database credentials and JWT secrets must be configured through environment variables.

## Author

Ramapradheesh V

## Project

RP Bank – Enterprise Java Full Stack Capstone Project

Built as part of the Java Full Stack Software Development Internship at RabTech Academy.
