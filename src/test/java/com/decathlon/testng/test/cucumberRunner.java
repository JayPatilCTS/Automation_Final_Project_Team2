package com.decathlon.testng.test;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/footer.feature"}, glue = {"com.decathlon.cucumber"})

public class cucumberRunner extends AbstractTestNGCucumberTests {

}
