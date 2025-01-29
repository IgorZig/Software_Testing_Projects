package ecommerce.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Wait;

import ecommerce.AbstractComponents.AbstractComponent;


public class RegistrationPage extends AbstractComponent {
 WebDriver driver;
 
 		//Constructor
		public RegistrationPage(WebDriver driver) {
			 super(driver);
		   this.driver = driver;
		   PageFactory.initElements(driver, this);
}

 // Locators for input fields
 @FindBy(id = "firstName")
 WebElement firstNameField;

 @FindBy(id = "lastName")
 WebElement lastNameField;

 @FindBy(id = "userEmail")
 WebElement emailField;

 @FindBy(id = "userMobile")
 WebElement phoneNumberField;

 @FindBy(xpath = "//select[@formcontrolname='occupation']")
 WebElement occupationDropdown;

 @FindBy(xpath = "//input[@value='Male']")
 WebElement maleRadioButton;

 @FindBy(xpath = "//input[@value='Female']")
 WebElement femaleRadioButton;

 @FindBy(id = "userPassword")
 WebElement passwordField;

 @FindBy(id = "confirmPassword")
 WebElement confirmPasswordField;

 @FindBy(xpath = "//input[@type='checkbox']")
 WebElement ageCheckbox;

 @FindBy(id = "login")
 WebElement registerButton;

 // Locators for error messages
 @FindBy(xpath = "//input[@id='firstName']/following-sibling::div")
 WebElement firstNameError;

 @FindBy(xpath = "//input[@id='lastName']/following-sibling::div")
 WebElement lastNameError;

 @FindBy(xpath = "//input[@id='userEmail']/following-sibling::div")
 WebElement emailError;

 @FindBy(xpath = "//input[@id='userMobile']/following-sibling::div")
 WebElement phoneNumberError;

 @FindBy(xpath = "//div[@aria-label='Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small']") 
 WebElement passwordError;

 @FindBy(xpath = "//input[@id='confirmPassword']/following-sibling::div")
 WebElement confirmPasswordError;

 @FindBy(xpath = "//input[@type='checkbox']/following-sibling::div")
 WebElement checkboxError;
 
 @FindBy(xpath = "//a[@class='text-reset']")
 WebElement registButton;

 
 // Page Actions
 public void goTo() {
     driver.get("https://rahulshettyacademy.com/client"); 
     registButton.click();
 }

 public void fillRegistrationForm(String firstName, String lastName, String email, String phoneNumber, String occupation, String gender, String password, String confirmPassword, boolean is18OrOlder) {
     firstNameField.clear();
     firstNameField.sendKeys(firstName);

     lastNameField.clear();
     lastNameField.sendKeys(lastName);

     emailField.clear();
     emailField.sendKeys(email);

     phoneNumberField.clear();
     phoneNumberField.sendKeys(phoneNumber);

     if (!occupation.isEmpty()) {
         occupationDropdown.sendKeys(occupation);
     }

     if (gender.equalsIgnoreCase("Male")) {
         maleRadioButton.click();
     } else if (gender.equalsIgnoreCase("Female")) {
         femaleRadioButton.click();
     }

     passwordField.clear();
     passwordField.sendKeys(password);

     confirmPasswordField.clear();
     confirmPasswordField.sendKeys(confirmPassword);

     if (is18OrOlder) {
         ageCheckbox.click();
     }
 }

 public void  submitFormSuccess() {
     registerButton.click();
     waitForElementToAppear(By.cssSelector(".toast-bottom-right"));
     
 }

 public void  submitForm() {
     registerButton.click();
     
     
 }

 // Get Error Messages
 public String getFirstNameError() {
     return firstNameError.getText();
 }

 public WebElement getLastNameError() {
     return lastNameError;
 }

 public String getEmailError() {
     return emailError.getText();
 }

 public String getPhoneNumberError() {
     return phoneNumberError.getText();
 }

 public String getPasswordError() {
     return passwordError.getText();
 }

 public String getConfirmPasswordError() {
     return confirmPasswordError.getText();
 }

 public String getCheckboxError() {
     return checkboxError.getText();
 }
}
