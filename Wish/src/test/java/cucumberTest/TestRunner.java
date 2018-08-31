package cucumberTest;
import org.junit.runner.RunWith;
import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(glue="stepdefinition",features="Feature",tags="@delete")
public class TestRunner {

}
	