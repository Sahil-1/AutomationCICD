package sahilmalekseleniumframework.Tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import sahilmalekseleniumframework.TestComponents.BaseTest;
import sahilmalekseleniumframework.pageobjects.CartPage;
import sahilmalekseleniumframework.pageobjects.CheckoutConfirmationPage;
import sahilmalekseleniumframework.pageobjects.CheckoutPage;
import sahilmalekseleniumframework.pageobjects.OrdersPage;
import sahilmalekseleniumframework.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest{
	
	/*
	//Accepting Data Provider as directly passed data as object.
	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(String userName, String password, String productName, String countryName) throws IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(userName, password);
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(productName);
		CartPage cartPage = productCatalogue.goToCart();
		Boolean math = cartPage.verifyProductDisplay(productName);
		Assert.assertTrue(math);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(countryName);
		CheckoutConfirmationPage checkoutConfirmationPage = checkoutPage.submitOrder();
		String confirmText = checkoutConfirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider = "getData",dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest(String userName, String password, String productName) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(userName, password);
		OrdersPage ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));
		
	}
	
	//Data Provider using directly passing data as object.
	@DataProvider
	public Object[][] getData() {
		return new Object[][] {{"sahiltest1@gmail.com","Sahiltest1","LG REFRIGERATOR","India"},{"sahiltest2@gmail.com","Sahiltest2","IPHONE 13 PRO","India"}};
	}*/
	
	//Accepting Data Provider as passed HashMap.
	@Test(dataProvider = "getData",groups = "Purchase")
	public void submitOrder(HashMap<String, String> data) throws IOException {
		
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("userName"), data.get("password"));
		List<WebElement> products = productCatalogue.getProductList();
		productCatalogue.addProductToCart(data.get("productName"));
		CartPage cartPage = productCatalogue.goToCart();
		Boolean math = cartPage.verifyProductDisplay(data.get("productName"));
		Assert.assertTrue(math);
		CheckoutPage checkoutPage = cartPage.goToCheckout();
		checkoutPage.selectCountry(data.get("countryName"));
		CheckoutConfirmationPage checkoutConfirmationPage = checkoutPage.submitOrder();
		String confirmText = checkoutConfirmationPage.getConfirmationMessage();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
	}
	
	@Test(dataProvider = "getData",dependsOnMethods = {"submitOrder"})
	public void orderHistoryTest(HashMap<String, String> data) {
		ProductCatalogue productCatalogue = landingPage.loginApplication(data.get("userName"), data.get("password"));
		OrdersPage ordersPage = productCatalogue.goToOrders();
		Assert.assertTrue(ordersPage.verifyOrderDisplay(data.get("productName")));
	}
	
	//Data Provider using HashMap.
	@DataProvider
	public Object[][] getData() throws IOException {
		/*
		//Passing hardcoded data on hashmap and passing that hashmap. 
		HashMap<String, String> hmap = new HashMap<String, String>();
		hmap.put("userName", "sahiltest1@gmail.com");
		hmap.put("password", "Sahiltest1");
		hmap.put("productName", "LG REFRIGERATOR");
		hmap.put("countryName", "India");
		
		HashMap<String, String> hmap1 = new HashMap<String, String>();
		hmap1.put("userName", "sahiltest2@gmail.com");
		hmap1.put("password", "Sahiltest2");
		hmap1.put("productName", "IPHONE 13 PRO");
		hmap1.put("countryName", "India");
		
		return new Object[][] {{hmap},{hmap1}};
		*/
		
		//Getting Data From JSON File and Convert it to HashMap and then pass that data to test case.
		List<HashMap<String, String>> data = getJsonDataToHashMap(System.getProperty("user.dir")+"//src//test//java//sahilmalekseleniumframework//data//PurchaseOrder.json");
		return new Object[][] {{data.get(0)},{data.get(1)}};
	}
}
