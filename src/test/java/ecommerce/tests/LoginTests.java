package ecommerce.tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.TestComponents.BaseTest;
import ecommerce.pageobjects.LandingPage;
import ecommerce.pageobjects.ProductCatalogue;

public class LoginTests extends BaseTest {

	@Test(dataProvider = "getData", groups = { "Login" })
	public void successfulLoginTest(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		Assert.assertTrue(productCatalogue.isCataloguePageDisplayed());
	}

	@Test
	public void invalidLoginTest() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		landingPage.loginApplication("invalidemail@example.com", "invalidPassword");
		String errorMessage = landingPage.getErrorMessage();
		Assert.assertEquals(errorMessage, "Incorrect email or password.");
	}

	@Test
	public void forgotPasswordLinkTest() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		Assert.assertTrue(landingPage.isForgotPasswordDisplayed(), "Forgot Password link is not displayed.");
	}

	@Test
	public void registerLinkTest() {
		LandingPage landingPage = new LandingPage(driver);
		landingPage.goTo();
		Assert.assertTrue(landingPage.isRegisterLinkDisplayed(), "Register link is not displayed.");
	}

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//ecommerce//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) } };

	}
}
