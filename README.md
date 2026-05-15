# 🏧 ATM Monitoring Backend Service

![Spring Boot](https://img.shields.io/badge/Spring%20Boot-6DB33F?logo=springboot&logoColor=white)
![Java](https://img.shields.io/badge/Java-24-orange)
![PostgreSQL](https://img.shields.io/badge/PostgreSQL-316192?logo=postgresql&logoColor=white)
![Docker](https://img.shields.io/badge/Docker-2496ED?logo=docker&logoColor=white)
![Status](https://img.shields.io/badge/status-learning-orange)

---

## 🏧 About the Project

This project is a **Spring Boot backend application** built to simulate an ATM Monitoring System.

The purpose of this project is **purely for learning and exploration**, especially to understand how enterprise backend systems are designed using modern backend technologies.

The application monitors ATM operational status and device health such as:
- Cash remaining status
- Card reader status
- Receipt printer status
- ATM connectivity status

---

## 🎯 Purpose

This project is created for:

* Learning **Spring Boot backend development**
* Practicing **REST API design**
* Understanding **Spring Data JPA & Specifications**
* Learning **PostgreSQL integration**
* Implementing **dynamic filtering**
* Practicing **exception handling**
* Understanding **Docker & Docker Compose**

> ⚠️ This is **not a production-ready application**. It is a personal learning project.

---

## 🛠️ Tech Stack

* ☕ Java 24
* 🌱 Spring Boot
* 🗄️ Spring Data JPA
* 🐘 PostgreSQL
* 🐳 Docker & Docker Compose
* 🧠 Lombok
* 📦 Maven

---

## 📂 Project Structure

```text
src/main/java/id/vanggraini/atm/monitoring_app
├── controller/         # REST Controllers
├── dto/                # Request & Response DTOs
├── entity/             # JPA Entities
├── enums/              # Enum Classes
├── exception/          # Exception Handlers
├── repository/         # JPA Repositories
├── service/            # Service Interfaces
├── service/impl/       # Service Implementations
├── specification/      # JPA Specifications
└── MonitoringAppApplication.java

src/main/resources
├── application.properties
└── data.sql           # Dummy ATM data
```

---

## 🚀 Getting Started

### Clone Repository

```bash
git clone <repository-url>
cd monitoring-app
```

---

## ▶️ Run Locally

### Build the Project

```bash
mvn clean package
```

### Run the Application

```bash
mvn spring-boot:run
```

---

## 🐳 Run with Docker Compose

```bash
docker compose up --build
```

---

## 🐘 PostgreSQL Configuration

Example datasource configuration:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/atm_monitoring
spring.datasource.username=postgres
spring.datasource.password=postgres
```

---

## 📌 Features

* ATM status monitoring
* Device health monitoring
* Dynamic filtering with JPA Specification
* AND / OR filtering support
* Exception handling
* PostgreSQL integration
* Dockerized backend service

---

## 📡 API Endpoints

### Endpoint

```http
POST /api/devices
```

---

### Request Body (Optional)

```json
{
  "atmStatuses": ["ONLINE"],
  "cashRemainingStatuses": ["LOW"],
  "cardReaderStatuses": ["ERROR", "WARNING"],
  "receiptPrinterStatuses": ["NORMAL"]
}
```

All fields are optional.

If request body is empty or null, the API returns all ATM data.

---

### Example curl

```bash
curl -X POST http://localhost:8081/api/devices \
-H "Content-Type: application/json" \
-d '{
  "atmStatuses": ["ONLINE"],
  "cardReaderStatuses": ["ERROR", "WARNING"]
}'
```

---

### Example Response

```json
[
  {
    "atmId": "ATM001",
    "atmStatus": "ONLINE",
    "cashRemainingStatus": "NORMAL",
    "cardReaderStatus": "WARNING",
    "receiptPrinterStatus": "NORMAL",
    "lastUpdated": "2026-05-15T10:15:30"
  }
]
```

---

## 📦 Enum Values

### ATMStatus

```text
ONLINE
OFFLINE
OUT_OF_SERVICE
```

### DeviceHealth

```text
NORMAL
LOW
EMPTY
FULL
NEAR_FULL
WARNING
ERROR
```

---

## ❌ Error Handling

Example ATM not found response:

```json
{
  "status": 404,
  "message": "ATM not found with id ATM999"
}
```

Example invalid enum request:

```json
{
  "status": 400,
  "message": "Invalid value 'LOL' for field 'receiptPrinterStatus'"
}
```

---

## 🧠 Learning Outcomes

By building this project, you will learn:

* How Spring Boot applications are structured
* How to build REST APIs
* How to connect PostgreSQL with Spring Boot
* How to implement JPA Specifications
* How to handle exceptions globally
* How Docker containers communicate
* How backend systems are organized in enterprise applications

---

## 🔮 Future Improvements

* JWT Authentication
* Kafka Event Streaming
* ATM Heartbeat Monitoring
* Redis Cache
* Prometheus Metrics
* Grafana Dashboard
* CI/CD Pipeline
* Kubernetes Deployment

---

## 🙋‍♂️ Author

This project is created for learning enterprise backend development using Spring Boot and PostgreSQL.

Feel free to explore and improve it!

---

## ⭐ Notes

If you find this project useful, feel free to give it a ⭐ on GitHub!