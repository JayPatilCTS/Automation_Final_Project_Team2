package com.decathlon.cucumber;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class steps {
    WebDriver driver;
    @Given("the user is on the homepage")
    public void navigateToHomePage() {
        driver=new ChromeDriver();
        driver.get("https://www.decathlon.in/");
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @When("the user clicks on {string}")
    public void the_user_clicks_on(String string) {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user switches to the new gift card window")
    public void the_user_switches_to_the_new_gift_card_window() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @When("the user adds the gift card to the cart")
    public void the_user_adds_the_gift_card_to_the_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }

    @Then("the gift card should be successfully added to the cart")
    public void the_gift_card_should_be_successfully_added_to_the_cart() {
        // Write code here that turns the phrase above into concrete actions
        throw new io.cucumber.java.PendingException();
    }
}


