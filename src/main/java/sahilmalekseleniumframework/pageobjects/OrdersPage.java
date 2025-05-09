package sahilmalekseleniumframework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrdersPage {
	
	WebDriver driver;
	
	public OrdersPage(WebDriver driver) {
		this.driver = driver;
		//To initialization of all the @findby below step is require
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> productNames;
	
	public boolean verifyOrderDisplay(String productName) {
		boolean math = productNames.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		return math;
	}
}
