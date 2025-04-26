package sahilmalekseleniumframework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage {
	
	WebDriver driver;
	
	public CartPage(WebDriver driver) {
		this.driver = driver;
		//To initialization of all the @findby below step is require
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css = ".totalRow button")
	WebElement checkoutBtn;
	
	public boolean verifyProductDisplay(String productName) {
		boolean math = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return math;
	}
	
	public CheckoutPage goToCheckout() {
		checkoutBtn.click();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		return checkoutPage;
	}
}
