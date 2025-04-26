package cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

//Similar to TestNG we are using @CucumberOptions to provide cucumber file, step definition file to this test runner.
//'feature' keyword is used to provide cucumber feature file.
//'glue' keyword is used to provide step definition file.
//'monochrome=true' is used to provide cucumber test output in readable format. by default cucumber provide output in non-readable format.
//'plugin' keyword is used for what kind of report you want. (exa: plugin={key:target}) key means what kind of report and target means where you want to store that report. 

@CucumberOptions(features = "src/test/java/cucumber", tags="@Regression" ,glue = "sahilmalekseleniumframework.StepDefinitions", monochrome = true, plugin = {"html:reports/cucumber.html"})
public class TestNGTestRunner extends AbstractTestNGCucumberTests{

}
