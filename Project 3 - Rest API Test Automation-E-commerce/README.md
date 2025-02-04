# Software-Testing- Rest API Test Automation Projects

![image](https://github.com/user-attachments/assets/d9cc0503-1741-420f-8cc1-3d89b23c5000)
[Watch the video](https://github.com/IgorZig/Software_Testing_Projects/blob/main/Project%203%20-%20Rest%20API%20Test%20Automation-E-commerce/ApiPetStoreAutomation.mp4)
[Watch the video](https://github.com/IgorZig/Software_Testing_Projects/blob/main/Project%203%20-%20Rest%20API%20Test%20Automation-E-commerce/JenkinsPetStore.mp4)

# PetStoreAutomation - REST API Testing Framework

## 📌 Overview
PetStoreAutomation is a **REST API Testing Framework** built using **RestAssured** and **TestNG**. It is designed to automate testing of the **Swagger Petstore API** (User module), covering **CRUD operations** like creating, retrieving, updating, and deleting users.

This framework follows **industry best practices** by implementing a modular structure with separate layers for endpoints, payloads, test components, and utilities.

---

## 🏗️ Architecture
The framework is structured into different packages to maintain modularity and reusability:

📂 **`src/test/java/api.endpoints`** → Defines API routes and methods.
- `Routes.java` → Stores all endpoint URLs.
- `UserEndPoints.java` → Contains reusable methods for API interactions.

📂 **`src/test/java/api.payload`** → Represents request body data models.
- `User.java` → User object model with getter/setter methods.

📂 **`src/test/java/api.test`** → Houses test cases for API validation.
- `UserTests.java` → Contains CRUD test cases.
- `DDTests.java` → Implements data-driven testing.

📂 **`src/test/java/api.utilities`** → Utility classes for logging, reporting, and data handling.
- `DataProviders.java` → Manages test data input.
- `ExtentReportManager.java` → Generates test execution reports.
- `XLUtility.java` → Handles Excel file operations for data-driven testing.

📂 **`src/test/java/api.TestComponents`** → Base setup and configuration.
- `BaseTest.java` → Initializes logger, test data, and assertions.

📂 **`src/test/resources/`** → Stores configurations.
- `log4j2.xml` → Logging configuration file.

📂 **`logs/`** → Contains execution logs.

📂 **`reports/`** → Stores Extent Reports for test results.

---

## 🔧 Technologies Used
- **Java** (JDK 1.8+)
- **RestAssured** (API testing)
- **TestNG** (Test framework)
- **Maven** (Dependency management)
- **Log4j2** (Logging)
- **Extent Reports** (Test execution reports)
- **Faker** (Dynamic test data generation)
- **Excel (Apache POI)** (Data-driven testing)
- **Jenkins** (Integrate with Jenkins for CI/CD pipeline execution)

---

## 🛠️ Installation & Setup

### 1️⃣ Clone the Repository
```sh
git clone https://github.com/your-username/PetStoreAutomation.git
```

### 2️⃣ Open in IDE
Import the project into **IntelliJ IDEA** or **Eclipse** as a **Maven project**.

### 3️⃣ Install Dependencies
Ensure **Maven** is installed and run:
```sh
mvn clean install
```

### 4️⃣ Run the Tests
Execute all test cases using:
```sh
mvn test
```
Or run `testng.xml` directly in the IDE.

---

## 🚀 Features & Test Scenarios
- ✅ **CRUD Operations on User API**
  - `POST /user` → Create user
  - `GET /user/{username}` → Retrieve user
  - `PUT /user/{username}` → Update user
  - `DELETE /user/{username}` → Delete user
- ✅ **Data-Driven Testing** using `@DataProvider`
- ✅ **Logging with Log4j2**
- ✅ **Test Execution Reports (Extent Reports)**
- ✅ **Dynamic Test Data Generation (Faker)**

---

## 📊 Test Reporting
After execution, test results can be found in:
```
/reports/index.html
```
Open `index.html` in a browser to view the test summary.

---


## 🤝 Contributing
Feel free to fork this repository and submit pull requests! 😊

---

## 📜 License
This project is open-source under the **MIT License**.


