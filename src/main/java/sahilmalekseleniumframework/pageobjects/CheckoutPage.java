package sahilmalekseleniumframework.pageobjects;

import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sahilmalekseleniumframework.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//To initialization of all the @findby below step is require
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".user__address input")
	WebElement country;
	
	@FindBy(css = ".list-group-item span")
	List<WebElement> countryList;
	
	@FindBy(css = ".action__submit")
	WebElement checkoutBtn;
	
	public void selectCountry(String countryName) {
		country.sendKeys(countryName);
		countryList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement :: click);
	}
	
	public CheckoutConfirmationPage submitOrder() {
		checkoutBtn.click();
		CheckoutConfirmationPage checkoutConfirmationPage = new CheckoutConfirmationPage(driver);
		return checkoutConfirmationPage;
	}
}
