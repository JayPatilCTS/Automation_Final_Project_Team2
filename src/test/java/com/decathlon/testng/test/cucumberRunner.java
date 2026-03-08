package com.decathlon.testng.test;

import io.cucumber.junit.Cucumber;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = {"src/test/resources/FooterPolicy.feature"}, glue = {"com.decathlon.cucumber"})
public class cucumberRunner extends AbstractTestNGCucumberTests {
}
