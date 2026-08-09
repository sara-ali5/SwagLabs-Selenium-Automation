# Swag Labs Automation Testing Project

## Overview

This project is an end-to-end automation testing framework developed for the **Swag Labs** web application.

The framework automates key user flows using **Selenium WebDriver** and **TestNG**, following the **Page Object Model (POM)** design pattern to provide a clean, maintainable, reusable, and scalable automation structure.

The project also includes **data-driven testing using JSON**, **TestNG test groups**, **Allure reporting**, screenshots for failed tests, and reusable utility methods.

### Application Under Test

https://www.saucedemo.com/

---

## Technologies & Tools

* Java 25
* Selenium WebDriver 4.39.0
* TestNG 7.10.2
* Maven
* Allure Report
* WebDriverManager
* JSON
* IntelliJ IDEA
* Git & GitHub

---

## Framework Design

The project follows the **Page Object Model (POM)** design pattern.

### Benefits

* Separates test logic from page elements.
* Improves code readability and maintainability.
* Provides reusable page methods.
* Reduces code duplication.
* Makes test cases easier to manage and scale.
* Improves synchronization using reusable explicit waits.

---

## Project Structure

```text
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
│   │       │   ├── InventoryPage.java
│   │       │   ├── CartPage.java
│   │       │   └── CheckoutPage.java
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
│               ├── InventoryTest.java
│               └── CartTest.java
│
├── testData.json
├── testng.xml
├── pom.xml
├── README.md
└── .gitignore
```

---

# Automated Test Coverage

## 1. Login Feature

The following scenarios are automated:

* ✅ Verify successful login using valid credentials.
* ✅ Verify login with invalid username/password.
* ✅ Verify login without entering password.

---

## 2. Inventory Feature

The following scenarios are automated:

* ✅ Verify successful navigation to the Inventory page.
* ✅ Verify Inventory page title.
* ✅ Verify shopping cart icon is displayed.
* ✅ Verify the number of products displayed.
* ✅ Verify product information and actions.

---

## 3. Cart Feature

The following scenarios are automated:

* ✅ Add products to the shopping cart.
* ✅ Verify the number of products added to the cart.
* ✅ Add multiple products and verify the cart contents.
* ✅ Remove a product from the cart.
* ✅ Verify the cart total price.
* ✅ Verify product information inside the cart.
* ✅ Verify cart navigation.
* ✅ Verify cart state after logout and login again.

---

## 4. Checkout Feature

The checkout flow includes:

* ✅ Navigate from the cart to checkout.
* ✅ Enter checkout information.
* ✅ Verify checkout information.
* ✅ Verify checkout totals.
* ✅ Verify checkout with an empty cart based on the actual application behavior.

---

## 5. Cart State After Logout/Login

A dedicated scenario verifies whether cart items persist after the user logs out and logs in again.

The test flow is:

```text
Login
   ↓
Add products to cart
   ↓
Verify cart contents
   ↓
Logout
   ↓
Login again
   ↓
Open cart
   ↓
Verify cart state
```

The assertion is based on the **actual behavior observed from the application**, rather than assuming that the cart state is preserved or cleared.

---

# Test Data Management

Test data is stored externally in a JSON file:

```text
testData.json
```

The test data contains information such as:

* Valid user credentials.
* Invalid user credentials.
* Empty/negative login data.
* Products used in cart test scenarios.
* Product-related test data.

Separating test data from test scripts makes the framework easier to maintain and extend.

---

# Test Execution

## Run Using IntelliJ IDEA

1. Open the project in IntelliJ IDEA.
2. Make sure Maven dependencies are installed.
3. Open `testng.xml`.
4. Run the TestNG suite.

---

## Run Using Maven

Install dependencies and build the project:

```bash
mvn clean install
```

Run the tests:

```bash
mvn test
```

---

# TestNG Configuration

The project uses a TestNG XML suite to organize and execute the automated tests.

Test groups include:

* **Smoke**
* **Regression**

The groups are configured through:

```text
testng.xml
```

This allows specific test groups to be executed independently when required.

---

# Synchronization

The framework uses Selenium's **explicit waits** to synchronize test execution with application behavior.

Examples include:

```java
ExpectedConditions.elementToBeClickable()
ExpectedConditions.visibilityOfElementLocated()
ExpectedConditions.urlContains()
ExpectedConditions.attributeToBe()
```

This helps reduce synchronization issues caused by dynamic page loading and improves test stability.

---

# Reporting

The project uses **Allure Report** for test execution reporting.

Generate the report using:

```bash
allure serve target/allure-results
```

The Allure report provides:

* Test execution status.
* Passed and failed test cases.
* Test descriptions.
* Test steps.
* Failure details.
* Screenshots for failed tests when configured.
* Test severity and story information.

The framework also uses Allure annotations such as:

```java
@Story
@Severity
@Description
@Step
```

to provide detailed test documentation in the report.

---

# WebDriver Management

The project uses **WebDriverManager** to automatically manage browser drivers.

No manual ChromeDriver configuration is required.

---

# Page Objects

The framework separates application pages into dedicated Page Object classes.

### LoginPage

Handles:

* Username input.
* Password input.
* Login.
* Login error messages.

### InventoryPage

Handles:

* Product selection.
* Adding products to cart.
* Removing products.
* Shopping cart navigation.
* Product information.
* Logout.

### CartPage

Handles:

* Cart items.
* Product information.
* Removing products.
* Cart totals.
* Checkout navigation.

### CheckoutPage

Handles:

* Checkout information.
* Checkout overview.
* Order totals.
* Completing checkout.

---

# Git Ignore

Generated and IDE-specific files are excluded from version control.

```text
.idea/
target/
allure-results/
allure-report/
*.iml
```

These files are generated automatically and should not be uploaded to GitHub.

---

# Key Features of the Framework

* ✅ Page Object Model (POM)
* ✅ Data-Driven Testing
* ✅ JSON Test Data
* ✅ Selenium WebDriver
* ✅ TestNG
* ✅ TestNG Groups
* ✅ Explicit Waits
* ✅ Reusable Base Page methods
* ✅ WebDriverManager
* ✅ Allure Reporting
* ✅ Failure Screenshots
* ✅ Maven
* ✅ Git & GitHub
* ✅ Positive and Negative Test Scenarios
* ✅ Cart and Checkout Automation
* ✅ Application behavior verification

---

# Repository Contents

The GitHub repository includes:

* ✅ Automation source code
* ✅ Test classes
* ✅ Page Object classes
* ✅ Base classes
* ✅ Utility classes
* ✅ Listeners
* ✅ JSON test data
* ✅ TestNG configuration
* ✅ Maven configuration
* ✅ README documentation
* ✅ `.gitignore`

---

# Author

**Sara Ali**

Software Testing / QA Automation
