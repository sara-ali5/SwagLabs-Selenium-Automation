# Swag Labs Automation Project

## Overview

This project is an automation testing framework developed for the Swag Labs web application.

The framework automates the main user flows using Selenium WebDriver and TestNG.  
It follows the Page Object Model (POM) design pattern to provide a clean, maintainable, and reusable automation structure.

Application Under Test:

https://www.saucedemo.com/

---

## Technologies & Tools

- Java
- Selenium WebDriver 4.39.0
- TestNG 7.10.2
- Maven
- Allure Report
- WebDriverManager
- IntelliJ IDEA
- Git & GitHub

---

## Framework Design

The project follows the **Page Object Model (POM)** design pattern.

Benefits:
- Separates test logic from page elements.
- Improves code readability and maintenance.
- Allows reusable page methods.
- Makes test cases easier to manage and scale.

---

## Project Structure

```
SwagLabsAutomationAssignment
│
├── src
│   │
│   ├── main
│   │   └── java
│   │       │
│   │       ├── Base
│   │       │   ├── BasePages.java
│   │       │   └── BaseTest.java
│   │       │
│   │       ├── Pages
│   │       │   ├── LoginPage.java
│   │       │   └── InventoryPage.java
│   │       │
│   │       ├── Utilities
│   │       │   └── DataDriven.java
│   │       │
│   │       └── Listeners
│   │
│   └── test
│       └── java
│           │
│           └── Tests
│               ├── LoginTest.java
│               └── InventoryTest.java
│
├── testData.json
├── testng.xml
├── pom.xml
├── README.md
└── .gitignore
```

---

## Automated Test Coverage

### Login Feature

The following scenarios are automated:

✅ Verify successful login using valid credentials.

✅ Verify login with invalid username/password.

✅ Verify login without entering password.

---

### Inventory Feature

The following scenarios are automated:

✅ Verify user is redirected to Inventory page.

✅ Verify Inventory page title.

✅ Verify shopping cart icon is displayed.

✅ Verify number of products displayed.

---

## Test Data Management

Test data is stored externally in a JSON file.

The JSON file contains:

- Valid user credentials.
- Invalid user credentials.
- Empty password test data.

This approach separates test data from test scripts and makes test maintenance easier.

---

## Test Execution

### Run using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure Maven dependencies are installed.
3. Run `testng.xml`.

---

### Run using Maven

Install dependencies:

```
mvn clean install
```

Run tests:

```
mvn test
```

---

## TestNG Configuration

The project uses TestNG XML configuration.

The suite contains:

- Smoke Tests
- Regression Tests

Test groups are managed using:

```
testng.xml
```

---

## Reporting

The project uses Allure Report for test execution reports.

After running tests, generate the report using:

```
allure serve target/allure-results
```

The report includes:

- Test execution status.
- Test steps.
- Failed test details.
- Screenshots (if configured).

---

## WebDriver Management

The project uses WebDriverManager to automatically manage browser drivers.

No manual ChromeDriver setup is required.

---

## Git Ignore

The following files are excluded from version control:

```
.idea/
target/
allure-results/
allure-report/
*.iml
```

These files are generated automatically and should not be uploaded to GitHub.

---

## Repository Contents

The GitHub repository includes:

✅ Source code  
✅ Test classes  
✅ Page Object classes  
✅ Utilities  
✅ Test data JSON file  
✅ TestNG configuration  
✅ Maven configuration  
✅ README documentation  

---

## Author

Sara Ali
