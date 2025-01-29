package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.TestComponents.BaseTest;
import ecommerce.data.DataGenerator;
import ecommerce.pageobjects.RegistrationPage;

public class UserRegistrationTests extends BaseTest {

	
	@Test(dataProvider = "getData", description = "TC_RF_001: Verify user registration with valid details")
	
	public void verifyRegistrationWithValidDetails(HashMap<String, String> input)
			throws IOException, InterruptedException {

		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();

		registrationPage.fillRegistrationForm(input.get("firstName"), input.get("lastName"), input.get("email"),
				input.get("phoneNumber"), input.get("occupation"), input.get("gender"), input.get("password"),
				input.get("confirmPassword"), Boolean.parseBoolean(input.get("isAdult")) // Convert string to boolean
		);
		registrationPage.submitFormSuccess();
		WebElement successMessage = driver
				.findElement(By.xpath("//h1[contains(text(), 'Account Created Successfully')]"));

		Assert.assertEquals(successMessage.getText(), "Account Created Successfully", "Success message text mismatch.");
	}

	@Test(description = "TC_RF_002: Validate error messages for missing mandatory fields")
	public void validateMandatoryFieldErrors() {

		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();

		registrationPage.fillRegistrationForm("", "", "", "", "", "", "", "", false);
		registrationPage.submitForm();

		Assert.assertEquals(registrationPage.getFirstNameError(), "*First Name is required",
				"First Name error message mismatch.");
		Assert.assertEquals(registrationPage.getLastNameError(), "*Last Name is required",
				"Last Name error message mismatch.");
		Assert.assertEquals(registrationPage.getEmailError(), "*Enter Valid Email", "Email error message mismatch.");
		Assert.assertEquals(registrationPage.getPhoneNumberError(), "*Phone Number is required",
				"Phone Number error message mismatch.");
		Assert.assertEquals(registrationPage.getPasswordError(), "*Password is required",
				"Password error message mismatch.");
		Assert.assertEquals(registrationPage.getConfirmPasswordError(), "*Confirm Password is required",
				"Confirm Password error message mismatch.");
		Assert.assertEquals(registrationPage.getCheckboxError(), "*Please check above checkbox",
				"Checkbox error message mismatch.");
	}

	@Test(description = "TC_RF_003: Validate email format during registration")
	public void validateEmailFormat() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();

		registrationPage.fillRegistrationForm("John", "Doe", "invalid-email", "1234567890", "Engineer", "Male",
				"StrongPass123!", "StrongPass123!", true);
		registrationPage.submitForm();

		Assert.assertEquals(registrationPage.getEmailError(), "*Enter Valid Email", "Email format validation failed");
	}

	@Test(description = "TC_RF_004: Verify password strength validation")
	public void verifyPasswordStrengthValidation() {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();

		registrationPage.fillRegistrationForm("John", "Doe", "do@example.com", "1234567891", "Engineer", "Male",
				"weakweak", "weakweak", true);
		registrationPage.submitForm();
		Assert.assertEquals(registrationPage.getPasswordError(),
				"Please enter 1 Special Character, 1 Capital 1, Numeric 1 Small",
				"Password strength validation failed.");
	}

	@DataProvider(name = "invalidEmailData")
	public Object[][] invalidEmailData() {
		return new Object[][] { { "plainaddress" }, { "@missingusername.com" }, { "username@.com.my" },
				{ "username@domain..com" }, { "username@domain,com" } };
	}

	@Test(description = "TC_RF_003: Validate multiple invalid email formats", dataProvider = "invalidEmailData")
	public void validateMultipleInvalidEmails(String email) {
		RegistrationPage registrationPage = new RegistrationPage(driver);
		registrationPage.goTo();

		registrationPage.fillRegistrationForm("John", "Doe", email, "1234567890", "Engineer", "Male", "StrongPass123!",
				"StrongPass123!", true);
		registrationPage.submitForm();

		Assert.assertEquals(registrationPage.getEmailError(), "*Enter Valid Email",
				"Email validation failed for: " + email);
	}
	
	@DataProvider(name = "getData")
    public Object[][] getData() throws IOException {
        DataGenerator dataGenerator = new DataGenerator();
        List<HashMap<String, String>> data = dataGenerator.generateData(5);  // Generate 5 records
        return new Object[][] { { data.get(0) }, { data.get(1) }, { data.get(2) }, { data.get(3) }, { data.get(4) } };
    }
}