# Student Score Management System

A  Spring Boot backend application designed to manage student academic records, grades, and test scores.

## 🚀 Features
- **Student Profiles:** Add, update, and manage student records.
- **Score Tracking:** Record, modify, and delete scores for various subjects.
- **Academic Reporting:** Calculate GPA, averages, and retrieve individual student performance reports.

## 🛠️ Tech Stack
- **Backend Framework:** Java, Spring Boot (Spring Web, Spring Data JPA, Lombok, Oracle Driver,Spring Boot DevTools, Validations, Springdoc OpenAPI )
- **Database:** Oracle Database
- **Build Tool:** Maven

## 📋 Prerequisites
Before running this project, ensure you have the following installed on your machine:
- **Java Development Kit (JDK):** Version 17 or higher
- **Oracle Database Server:** Local instance or cloud-hosted 
- **Maven:** (Optional, as you can use the bundled Maven wrapper `./mvnw`)

## ⚙️ Configuration

Before launching the app, you must update your connection properties to point to your Oracle database. Open your configuration file at `src/main/resources/application.properties` and replace the placeholder credentials:

```properties
# Oracle Database Connection Setup
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:xe
spring.datasource.username=YOUR_ORACLE_USERNAME
spring.datasource.password=YOUR_ORACLE_PASSWORD
spring.datasource.driver-class-name=oracle.jdbc.OracleDriver

# Hibernate Properties
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.OracleDialect
spring.jpa.hibernate.ddl-auto=validate
debug=true
springdoc.swagger-ui.path=/swagger-ui.html
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.OracleDialect
```
*Note: Adjust the port (`1521`) and SID/Service Name (`xe`) to match your specific Oracle Database setup.*

## Getting Started


### 1. Clone the repository
```bash
git clone https://github.com/KanekaVong/SSMS.git
cd SSMS
```

### 2. Build the project
Compile the source code and download the necessary Maven dependencies using the wrapper:
```bash
./mvnw.cmd clean package
```


### 3. Run the application
Launch the Spring Boot server:
```bash
./mvnw.cmd spring-boot:run
```


The server will start up and listen for traffic by default at `http://localhost:8080`.
