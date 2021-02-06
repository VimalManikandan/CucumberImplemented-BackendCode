package loan.cucumber.cucumberOptions;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/loan/cucumber",
		glue="loan/cucumber/stepDefinations")
public class TestRunner {

}
