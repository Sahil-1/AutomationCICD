package sahilmalekseleniumframework.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		WebDriverManager.chromedriver().setup();
		WebDriver driver = new ChromeDriver();
		
		String productName = "LG REFRIGERATOR";
		String countryName = "India";
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.get("https://rahulshettyacademy.com/client");
		driver.manage().window().maximize();
		
		WebDriverWait Ewait = new WebDriverWait(driver, Duration.ofSeconds(5));
		
		driver.findElement(By.id("userEmail")).sendKeys("sahiltest1@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Sahiltest1");
		driver.findElement(By.id("login")).click();
		
		Ewait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		List<WebElement> products = driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod = products.stream().filter(product -> product.findElement(By.tagName("b")).getText().equals(productName)).findFirst().orElse(null);
		prod.findElement(By.cssSelector("button:last-of-type")).click();
		
		Ewait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
		Ewait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
		boolean math = cartProducts.stream().anyMatch(product -> product.getText().equalsIgnoreCase(productName));
		Assert.assertTrue(math);
		
		driver.findElement(By.cssSelector(".totalRow button")).click();
		
		driver.findElement(By.cssSelector(".user__address input")).sendKeys("Ind");
		List<WebElement> countryList = driver.findElements(By.cssSelector(".list-group-item span"));
		countryList.stream().filter(country -> country.getText().equalsIgnoreCase(countryName)).findFirst().ifPresent(WebElement :: click);
		
		driver.findElement(By.cssSelector(".action__submit")).click();
		
		Ewait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(".hero-primary")));
		String confirmText = driver.findElement(By.cssSelector(".hero-primary")).getText();
		Assert.assertEquals(confirmText, "THANKYOU FOR THE ORDER.");
		
		driver.close();
	}

}
