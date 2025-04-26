package sahilmalekseleniumframework.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import sahilmalekseleniumframework.AbstractComponents.AbstractComponent;

public class CheckoutConfirmationPage extends AbstractComponent{
	
	WebDriver driver;
	
	public CheckoutConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		//To initialization of all the @findby below step is require
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = ".hero-primary")
	WebElement confirmationMessage;
	
	By confirmMsg = By.cssSelector(".hero-primary");
	
	public String getConfirmationMessage() {
		WaitForElementToAppear(confirmMsg);
		return confirmationMessage.getText();
	}
}
