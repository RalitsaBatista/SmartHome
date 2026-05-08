# 🏠 SmartHome Application

A robust **Spring Boot** web application designed to manage and monitor smart home devices. 
This project features a modular dashboard, localized user interfaces, and integrated control systems for lighting, climate, and security.

---

## 📂 Project Structure

The application follows a standard Maven/Spring Boot directory layout:

```text
smarthome/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com.example.smarthome/
│   │   │       ├── model/
│   │   │       │   ├── Device.java                 # JPA entity for smart home devices
│   │   │       │   └── User.java                   # JPA entity for registered users
│   │   │       │
│   │   │       ├── repository/
│   │   │       │   ├── DeviceRepository.java       # Spring Data JPA repository for devices
│   │   │       │   └── UserRepository.java         # Spring Data JPA repository for users
│   │   │       │
│   │   │       ├── service/
│   │   │       │   ├── DeviceService.java          # Business logic for device management
│   │   │       │   └── UserService.java            # Registration and login validation logic
│   │   │       │
│   │   │       ├── HomeController.java             # Routes and view controller logic
│   │   │       ├── LocaleConfig.java               # Internationalization/language configuration
│   │   │       └── SmarthomeApplication.java       # Main Spring Boot application entry point
│   │   │
│   │   └── resources/
│   │       ├── static/
│   │       │   └── css/
│   │       │       └── dashboard.css               # Global dashboard and form styling
│   │       │
│   │       ├── templates/
│   │       │   ├── index.html                      # Main dashboard page
│   │       │   ├── login.html                      # Login page
│   │       │   ├── register.html                   # User registration page
│   │       │   ├── lighting.html                   # Lighting device control page
│   │       │   ├── climate.html                    # Climate/thermostat page
│   │       │   ├── security.html                   # Security system page
│   │       │   ├── settings.html                   # User and device settings page
│   │       │   └── SmartHomeApp.html               # Additional layout/view file
│   │       │
│   │       ├── application.properties              # Application, database, and JPA configuration
│   │       ├── messages.properties                 # Default translation messages
│   │       └── messages_bg.properties              # Bulgarian translation messages
│   │
│   └── test/
│       └── java/
│           └── com.example.smarthome/
│               └── SmarthomeApplicationTests.java  # Spring Boot context test
│
├── smartHomeDB.mv.db                               # Local H2 database file
├── smartHomeDB.trace.db                            # H2 trace/debug file
├── Dockerfile                                      # Docker build/runtime configuration
├── mvnw                                            # Maven wrapper script for Unix/macOS
├── mvnw.cmd                                        # Maven wrapper script for Windows
├── pom.xml                                         # Maven dependencies and build configuration
├── README.md                                      # Project documentation
├── .gitignore
└── .gitattributes
```