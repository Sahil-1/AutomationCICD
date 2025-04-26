package sahilmalekseleniumframework.TestComponents;

import java.io.IOException;

import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;

import sahilmalekseleniumframework.Resources.ExtentReporterNG;

public class Listeners extends BaseTest implements ITestListener{
	
	ExtentTest test;
	ExtentReports extent = ExtentReporterNG.getReportObject();
	
	//In Parallel Execution "ExtentTest test" is getting override with another test case. at a time it creates only one instance and in parallel execution multiple test cases are using it.
	//To overcome above problem we need to use "ThreadLocal" class object. which will allocate "ExtentTest test" to each and every test case separately so it will not override as every test case have it's own "ExtentTest test".
	ThreadLocal<ExtentTest> threadLocalTest = new ThreadLocal<ExtentTest>(); //Thread safe
	
	@Override
	public void onTestStart(ITestResult result) {
		// TODO Auto-generated method stub
		test = extent.createTest(result.getMethod().getMethodName());
		threadLocalTest.set(test); //it will assign unique thread id to the test case.
	}
	
	@Override
	public void onTestSuccess(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocalTest.get().log(Status.PASS, "Test Passed");
	}
	
	@Override
	public void onTestFailure(ITestResult result) {
		// TODO Auto-generated method stub
		threadLocalTest.get().fail(result.getThrowable());
		
		//Take Screenshot
		try {
			driver = (WebDriver) result.getTestClass().getRealClass().getField("driver").get(result.getInstance());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		String screenshotFilePath = null;
		try {
			screenshotFilePath = getScreenshot(result.getMethod().getMethodName(), driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//Attache to the Report
		threadLocalTest.get().addScreenCaptureFromPath(screenshotFilePath, result.getMethod().getMethodName());
	}
	
	@Override
	public void onFinish(ITestContext context) {
		// TODO Auto-generated method stub
		extent.flush();
	}
}
