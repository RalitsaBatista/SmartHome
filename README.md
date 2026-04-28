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
│   │   │       ├── SmarthomeApplication.java  # Main application entry point
│   │   │       ├── HomeController.java         # Routes and view controllers
│   │   │       └── LocaleConfig.java           # i18n and Language configuration
│   │   │
│   │   └── resources/
│   │       ├── static/css/
│   │       │   └── dashboard.css               # Global application styling
│   │       ├── templates/                      # Thymeleaf HTML views
│   │       │   ├── index.html                  # Main Dashboard
│   │       │   ├── lighting.html               # Light control interface
│   │       │   ├── climate.html                # HVAC and temperature settings
│   │       │   ├── security.html               # Security system monitoring
│   │       │   ├── settings.html               # User & system preferences
│   │       │   └── SmartHomeApp.html           # Core layout wrapper
│   │       ├── application.properties          # Server & Spring configurations
│   │       ├── messages.properties             # Default (English) translations
│   │       └── messages_bg.properties          # Bulgarian translations
│   └── test/                                   # Unit and integration tests
└── pom.xml                                     # Project dependencies and build config