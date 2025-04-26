package sahilmalekseleniumframework.Tests;

import java.io.IOException;
import org.testng.Assert;
import org.testng.annotations.Test;
import sahilmalekseleniumframework.TestComponents.BaseTest;
import sahilmalekseleniumframework.TestComponents.Retry;

public class LoginErrorValidationTest extends BaseTest{
	
	String userName = "sahiltes2@gmail.com";
	String password = "Sahiltes2";
	
	@Test(groups = {"ErrorValidations"}, retryAnalyzer = Retry.class)
	public void loginErrorValidation() throws IOException {
		
		landingPage.loginApplication(userName, password);
		System.out.println("Failing This Test For Testing");
		Assert.assertEquals("Incorrec email or password.", landingPage.getErrorMessage());
	}

}
