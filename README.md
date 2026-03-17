# 🚀 NexusTestFramework

A scalable UI test automation framework designed using **Java, Selenium WebDriver, and TestNG**, following industry-standard design patterns such as **Page Object Model (POM)**.

This framework is designed to simulate real-world QA automation practices including **maintainability, scalability, and team collaboration**.

---

## 🧠 Tech Stack

- Java (Amazon Corretto)
- Selenium WebDriver
- TestNG
- Maven
- WebDriverManager
- Logback (Logging)

---

## 🏗️ Framework Design

The framework follows best practices for maintainability and scalability:

- **Page Object Model (POM)** → separates UI logic from test logic  
- **Base Test Class** → handles test setup and teardown  
- **Driver Factory** → centralized and reusable WebDriver initialization  
- **Configuration Management** → environment-based execution via `config.properties`  
- **Logging** → structured logging using Logback  
- **Reusable Utilities** → common helper methods organized under `utils`  

---

## 🎯 Key Features

- Supports multi-browser execution  
- Externalized configuration via properties file  
- Modular and reusable test structure  
- Clean separation between test logic and UI interactions  

---

## 📁 Project Structure
src/test/java
├── pages # Page classes (UI interactions)
├── tests # Test cases (TestNG)
└── utils # Framework core utilities
├── BaseClass
├── BrowserType
├── ConfigManager
├── ConfigReader
└── Constants

src/test/resources
└── config
└── config.properties

---

## ▶️ How to Run Tests

### Option 1: IntelliJ
- Right-click on `LoginTest`
- Click **Run**

### Option 2: Maven

```bash
mvn clean test

---
