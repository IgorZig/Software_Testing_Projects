package ecommerce.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import ecommerce.TestComponents.BaseTest;
import ecommerce.pageobjects.CartPage;
import ecommerce.pageobjects.CheckoutPage;
import ecommerce.pageobjects.ConfirmationPage;
import ecommerce.pageobjects.OrderPage;
import ecommerce.pageobjects.ProductCatalogue;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

public class SubmitOrderTest extends BaseTest {
	String productName = "QWERTY";

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void submitOrder(HashMap<String, String> input) throws IOException, InterruptedException {

		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalogue.goToCartPage();

		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		// Scroll to the bottom of the page
		//((JavascriptExecutor) driver).executeScript("window.scrollTo(0, document.body.scrollHeight);");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

	}

	@Test(dataProvider = "getData", groups = { "Purchase" })
	public void OrderHistoryTest(HashMap<String, String> input) throws IOException, InterruptedException {
		// "QWERTY";
		ProductCatalogue productCatalogue = landingPage.loginApplication(input.get("email"), input.get("password"));
		OrderPage ordersPage = productCatalogue.goToOrdersPage();
		Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

	}

	

	@DataProvider
	public Object[][] getData() throws IOException {

		List<HashMap<String, String>> data = getJsonDataToMap(
				System.getProperty("user.dir") + "//src//test//java//ecommerce//data//PurchaseOrder.json");
		return new Object[][] { { data.get(0) } };

	}

}
