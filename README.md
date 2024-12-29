# Tourism Project

## Overview

The "Tourism" project is a Java-based web application designed to manage tourists, bookings, and tours efficiently. The application integrates with a relational database to store and retrieve data. The project focuses on providing a user-friendly interface for handling tourism-related operations while ensuring robust backend functionality.

## Features

- **Tourist Management**: Add, update, and delete tourist details.
- **Booking Management**: Manage bookings and associate them with tourists.
- **Tour Management**: Organize and categorize various tour packages.
- **Steampunk-themed Interface**: A visually appealing user interface with a unique steampunk design.

## Technology Stack

- **Backend**: Java (Spring Boot Framework)
- **Frontend**: Thymeleaf templates, HTML5, CSS3
- **Database**: PostgreSQL
- **Build Tool**: Maven
- **Deployment**: Embedded Tomcat Server (packaged with Spring Boot)

---

## Prerequisites

1. **Java**:

   - Install JDK version 11 or higher.
   - [Download JDK](https://www.oracle.com/java/technologies/javase-downloads.html).
   - Verify installation:
     ```
     java -version
     ```

2. **PostgresSQL Database**:

   - Install PostgreSQL (version 8.0 or higher).
   - Install pgAdmin4.
   - Set up a database named `tourism`.

3. **Maven**:

   - Install Maven build tool.
   - [Download Maven](https://maven.apache.org/download.cgi).
   - Verify installation:
     ```
     mvn -version
     ```

4. **Git** (optional, for cloning the repository):

   - [Download Git](https://git-scm.com/).

---

## Installation

### Step 1: Clone the Repository

Clone the project repository to your local machine:

```bash
git clone https://github.com/3Mira3/Project-Tourism
cd <.../tourism>
```

### Step 2: Configure the Database

1. Open PostgreSQL Workbench or any database client.
2. Create a new database:
   ```sql
   CREATE DATABASE tourism;
   ```

All tables will be automatically created in the database thanks to the "migration" feature.
   
4. Open the file `src/main/resources/application.properties` and configure the database connection:
   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/tourism
   spring.datasource.username=your_mysql_username
   spring.datasource.password=your_mysql_password

   spring.jpa.hibernate.ddl-auto=update
   spring.jpa.show-sql=true
   ```

### Step 3: Build the Project

Build the project using Maven:

```bash
mvn clean install
```

### Step 4: Run the Application

Start the Spring Boot application:

```bash
mvn spring-boot:run
```

Access the application at: `http://localhost:8080`

---

## Project Structure

```
├── src
│   ├── main
│   │   ├── java
│   │   │   └── stu.cn.ua.tourism
│   │   │       ├── config
│   │   │       ├── controllers
│   │   │       ├── models
│   │   │       ├── repositories
│   │   │       ├── security
│   │   │       ├── services
│   │   │       └── TourismApplication.java
│   │   ├── resources
│   │   │   ├── static
│   │   │   │   ├── css
│   │   │   │   └── images
│   │   │   ├── templates
│   │   │   │   └── html
│   │   │   └── application.properties
│   └── test
│       └── java
│           └── stu.cn.ua.tourism
                └── TourismApplicationTests
├── pom.xml
├── gitignore
├── mvnw
└── README.md

```

### Key Directories:

- `controllers/`: Handles HTTP requests and defines endpoints.
- `models/`: Contains entity classes mapped to database tables.
- `repositories/`: Provides CRUD operations for the database.
- `services/`: Contains business logic.
- `templates/`: Thymeleaf HTML templates.
- `application.properties`: Configuration for the project.

---

## Database Schema

Ensure the following tables are created in the database:

### `tourists` Table:

```sql
CREATE TABLE tourists (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(100) NOT NULL UNIQUE
);
```

### `bookings` Table:

```sql
CREATE TABLE bookings (
    id INT AUTO_INCREMENT PRIMARY KEY,
    tourist_id INT NOT NULL,
    tour_id INT NOT NULL,
    booking_date DATE NOT NULL,
    FOREIGN KEY (tourist_id) REFERENCES tourists(id),
    FOREIGN KEY (tour_id) REFERENCES tours(id)
);
```

### `tours` Table:

```sql
CREATE TABLE tours (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(150) NOT NULL,
    description TEXT NOT NULL
);
```

### `booking_items` Table:

```sql
CREATE TABLE booking_items (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    booking_id BIGINT NOT NULL,
    description VARCHAR(255),
    FOREIGN KEY (booking_id) REFERENCES bookings(id)
);
```

### `users` Table:

```sql
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(20) NOT NULL
);
```

---

## Usage

### Adding Tourists

1. Navigate to the `Tourists` section.
2. Click on `Add Tourist` and provide the required details.

### Creating Bookings

1. Go to the `Bookings` section.
2. Select a tourist and an available tour package.
3. Confirm the booking details.

### Managing Tours

1. Use the `Tours` section to view, add, or edit tour packages.

### Managing Booking Items

1. Navigate to the Bookings section.

2. Select a specific booking to view or manage associated items.

3. Add, update, or delete items related to the booking.

### Managing Users

1. Go to the Users section (admin access required).

2. Add or modify user accounts.

3. Assign roles (e.g., admin, user) to control access levels.

---

## Troubleshooting

1. **Application does not start**:

   - Check the database credentials in `application.properties`.
   - Verify that the PostgreSQL service is running.

2. **Database connection error**:

   - Ensure the `tourism` database exists.
   - Verify that your PostgreSQL username and password are correct.

3. **Static resources not loading**:

   - Clear your browser cache.
   - Ensure the paths in Thymeleaf templates are correct.

---

### View of login page

![Main Page_Screenshot](/src/main/resources/static/css/images/login_page.png)

---

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.

---

## Acknowledgments

- Steampunk design inspirations.
- Thymeleaf and Spring Boot documentation.

Feel free to contribute to the project by submitting issues or pull requests!

