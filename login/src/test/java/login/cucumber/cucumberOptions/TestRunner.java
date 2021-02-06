package login.cucumber.cucumberOptions;



import org.junit.runner.RunWith;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;


@RunWith(Cucumber.class)
@CucumberOptions(
		features = "src/test/java/login/cucumber",
		glue="login/cucumber/stepDefinations")
public class TestRunner {

}
