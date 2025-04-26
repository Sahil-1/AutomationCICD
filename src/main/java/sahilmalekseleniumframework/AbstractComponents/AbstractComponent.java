package sahilmalekseleniumframework.AbstractComponents;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import sahilmalekseleniumframework.pageobjects.CartPage;
import sahilmalekseleniumframework.pageobjects.OrdersPage;

public class AbstractComponent {
	
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		//To initialization of all the @findby below step is require
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "[routerlink*='cart']")
	WebElement CartHeader;
	
	@FindBy(css = "[routerlink*='orders']")
	WebElement OrdersHeader;

	public void WaitForElementToAppear(By findBy) {
		WebDriverWait Ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Ewait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}
	
	public void WaitForWebElementToAppear(WebElement findBy) {
		WebDriverWait Ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Ewait.until(ExpectedConditions.visibilityOf(findBy));
	}
	
	public void WaitForElementToDisappear(WebElement element) {
		WebDriverWait Ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
		Ewait.until(ExpectedConditions.invisibilityOf(element));
	}
	
	public CartPage goToCart() {
		CartHeader.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrdersPage goToOrders() {
		OrdersHeader.click();
		OrdersPage ordersPage = new OrdersPage(driver);
		return ordersPage;
	}
}
