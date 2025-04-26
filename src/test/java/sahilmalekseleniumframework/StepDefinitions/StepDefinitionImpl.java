package sahilmalekseleniumframework.StepDefinitions;

import java.io.IOException;
import java.util.List;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sahilmalekseleniumframework.TestComponents.BaseTest;
import sahilmalekseleniumframework.pageobjects.CartPage;
import sahilmalekseleniumframework.pageobjects.CheckoutConfirmationPage;
import sahilmalekseleniumframework.pageobjects.CheckoutPage;
import sahilmalekseleniumframework.pageobjects.LandingPage;
import sahilmalekseleniumframework.pageobjects.ProductCatalogue;


public class StepDefinitionImpl extends BaseTest{
	
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public CheckoutConfirmationPage checkoutConfirmationPage;
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		landingPage = launchApplication();
	}
	
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username, String password) {
		productCatalogue = landingPage.loginApplication(username, password);
	}
	
	@When("^I add product (.+) to cart$")
	public void i_add_product_to_cart(String productName) {
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
	}
	
	@And("^Checkout (.+) and submit the order$")
	public void checkout_and_submit_the_order(String productName) {
		CartPage cartPage = productCatalogue.goToCart();
		Boolean math = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(math);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry("India");
		checkoutConfirmationPage = checkoutPage.submitOrder();
	}
	
	@Then("{string} message is displayed on confirmationPage")
	public void message_is_displayed_on_confirmationPage(String string) {
		String confirmText = checkoutConfirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void message_is_displayed(String string) {
		Assert.assertEquals(string, landingPage.getErrorMessage());
		driver.close();
	}
}
