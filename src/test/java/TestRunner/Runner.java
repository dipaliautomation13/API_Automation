package TestRunner;

import io.cucumber.junit.Cucumber;
import org.junit.runner.RunWith;

import io.cucumber.junit.CucumberOptions;
@RunWith(Cucumber.class)
@CucumberOptions(
        //path of feature file
        features = "src/test/java/features/createProduct.feature",
        //path of step definition file
        glue = "stepDefinitions"
)
public class Runner {
}