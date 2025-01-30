```markdown
# 🛒 Selenium Automation Framework - E-commerce  
**Author:** Igor Zig 

## 🛠️ Tech Stack  
- **Java** (JDK 1.8)  
- **Selenium WebDriver**  
- **TestNG** (for test execution)  
- **Maven** (dependency management)  
- **Page Object Model (POM)** (for maintainability)  
- **Thread Local Support** (for thread safety in parallel execution)  
- **Jenkins** (for CI/CD)  

## 📂 Project Structure  

SeleniumFrameworkDesign
│── src
│   ├── main/java
│   │   ├── ecommerce.AbstractComponents      # Reusable components
│   │   │   ├── AbstractComponent.java
│   │   ├── ecommerce.pageobjects             # Page Object Model (POM)
│   │   │   ├── CartPage.java
│   │   │   ├── CheckoutPage.java
│   │   │   ├── ConfirmationPage.java
│   │   │   ├── LandingPage.java
│   │   │   ├── OrderPage.java
│   │   │   ├── ProductCatalogue.java
│   │   │   ├── RegistrationPage.java
│   │   ├── ecommerce.resources               # Configurations & Reports
│   │   │   ├── ExtentReporterNG.java
│   │   │   ├── GlobalData.properties
│   ├── test/java
│   │   ├── cucumber                          # BDD feature files & runner
│   │   │   ├── TestNGTestRunner.java
│   │   │   ├── SubmitOrder.feature
│   │   ├── ecommerce.data                    # Test Data
│   │   │   ├── DataGenerator.java            # Generates dynamic test data
│   │   │   ├── DataReader.java               # Reads external data sources
│   │   │   ├── PurchaseOrder.json            # JSON test data for purchase flow
│   │   │   ├── RegistrationName.json         # JSON test data for registration
│   │   ├── ecommerce.stepDefinitions         # Step Definitions (BDD)
│   │   │   ├── StepDefinitionImpl.java
│   │   ├── ecommerce.TestComponents          # TestNG Test Components
│   │   │   ├── BaseTest.java
│   │   │   ├── Listeners.java
│   │   │   ├── Retry.java
│   │   ├── ecommerce.tests                   # Tests
│   │   │   ├── LoginTests.java
│   │   │   ├── SubmitOrderTest.java
│   │   │   ├── UserRegistrationTests.java
│── testSuites                                 # XML test suite files
│   │── Purchase.xml
│   │── Sanity.xml
│   │── Smoke.xml
│── reports                                    # Test execution reports
│── pom.xml                                    # Maven dependencies

```

### 🔹 **Test Data Usage**
- **DataGenerator.java** → Generates random test data (names, emails, etc.).
- **DataReader.java** → Reads data from JSON files and external sources.
- **PurchaseOrder.json** → Contains test scenarios for purchase flows.
- **RegistrationName.json** → Stores test data for user registration.

---

## 🚀 Features  
- **TestNG & Annotations** → Supports `@Test`, `@BeforeMethod`, `@AfterMethod`  
- **Parallel Execution** → Enabled via TestNG XML  
- **Thread Safety** → Implemented using ThreadLocal  
- **BDD Support** → Cucumber feature files & step definitions  
- **Data-Driven Testing** → JSON & External Test Data (Apache POI for Excel)  
- **Logging & Reporting** → TestNG Reports, Extent Reports  
- **Jenkins Integration** → Automated test execution in CI/CD pipelines  
- **Retry Mechanism** → Implemented for flaky tests  
- **Selenoid & Cloud Grids** → Run tests on BrowserStack & LambdaTest  

---

### ✅ **Running Tests with TestNG**
```sh
mvn test
```

### ✅ **Running a Specific Test Suite**
```sh
mvn test -DsuiteXmlFile=testSuites/Smoke.xml
```

---

### 🤖 **Jenkins Integration**
This project is **CI/CD** with Jenkins.  

#### ✅ **Running Tests in Jenkins**
1. Install **Maven Plugin** in Jenkins.  
2. Create a new **Jenkins Job** → Select "Freestyle Project."  
3. Configure the **Git Repository URL**.  
4. In **Build Steps**, select:  
   ```sh
   mvn clean test
   ```
5. Save and **Build Now** to trigger the tests.  

---

## 📊 **Reports**  
- **Extent Reports** → Generates detailed test execution reports  
- **TestNG Reports** → Default reporting mechanism  

---

## 📬 **Contact**  
For any queries or contributions, feel free to reach out!  
```
