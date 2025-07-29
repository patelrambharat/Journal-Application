# 📝 Journal Application

A **Journal Application** built using **Spring Boot**, **Spring Security**, **JWT Authentication**, **MySQL Database**, and **Thymeleaf**. This web-based application allows users to create, edit, and manage their personal journal entries securely.

---

## 🌟 Features

- User Registration & Login
- JWT-based Authentication & Authorization
- Role-Based Access Control (Admin/User)
- CRUD Operations on Journal Entries
- Responsive UI with Thymeleaf Templates
- Password Encryption using BCrypt
- Exception Handling with ControllerAdvice
- RESTful API Integration
- Database Integration with JPA & Hibernate
- API Documentation with Swagger UI

---

## 🛠️ Tech Stack

| Layer                  | Technology                                |
|------------------------|-------------------------------------------|
| Backend Framework       | Spring Boot                              |
| Security                | Spring Security, JWT (JSON Web Token)    |
| Database                | MySQL, Spring Data JPA, Hibernate        |
| Frontend Template Engine| Thymeleaf                                |
| API Testing             | Postman                                  |
| API Documentation       | Swagger (OpenAPI)                        |
| Build Tool              | Maven                                    |
| IDE                     | IntelliJ IDEA / Eclipse                  |
| Version Control         | Git & GitHub                             |

---

## 🏗️ Project Structure

src/
├── main/
│ ├── java/
│ │ └── com.patelRestaurant.journalApp
│ │ ├── controller
│ │ ├── service
│ │ ├── model
│ │ ├── repository
│ │ └── security
│ └── resources/
│ ├── templates/
│ ├── application.properties
│ └── static/
└── test/


---

## ⚙️ Installation & Run

### Prerequisites:
- Java 17+
- Maven
- MySQL
- IntelliJ IDEA / Eclipse

### Steps:
1. **Clone the Repository**
   ```bash
   git clone https://github.com/patelrambharat/Journal-Application.git
   cd Journal-Application
Configure Database

Update application.properties:
spring.datasource.url=jdbc:mysql://localhost:3306/journal_app
spring.datasource.username=your_username
spring.datasource.password=your_password

Run the Application

mvn spring-boot:run
Access the Application

Frontend: http://localhost:8080/

Swagger UI: http://localhost:8080/swagger-ui.html

🛡️ Security & Authentication
JWT token is generated upon successful login.

Passwords are encrypted using BCryptPasswordEncoder.

Only authenticated users can access protected endpoints.

Admin Role can manage all users and entries.

📂 API Endpoints (Sample)
Method	Endpoint	Description
POST	/api/auth/register	User Registration
POST	/api/auth/login	User Login (JWT Generation)
GET	/api/journals	Get All Journal Entries
POST	/api/journals	Create New Journal Entry
PUT	/api/journals/{id}	Update Journal Entry
DELETE	/api/journals/{id}	Delete Journal Entry

🖥️ Screenshots
You can add screenshots of your UI here for better presentation.

📌 Future Enhancements
OAuth2 Integration (Google/Facebook Login)

Profile Picture Upload

Commenting on Journal Entries

Pagination & Search Features

Deploy on AWS/GCP

🤝 Contribution
Feel free to fork this repository and create Pull Requests. Open issues for suggestions or bugs.

📧 Contact
Rambharat Patel
LinkedIn | GitHub | patelrambharat@gmail.com
