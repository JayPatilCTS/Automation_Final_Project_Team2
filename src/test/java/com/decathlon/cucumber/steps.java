package com.decathlon.cucumber;

import io.cucumber.java.en.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;

public class steps {

    WebDriver driver;

    @Given("User is on the Homepage")
    public void user_is_on_the_homepage() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.decathlon.in/");
    }

    @When("User scrolls down to the footer section")
    public void user_scrolls_down_to_the_footer_section() {
        WebElement footer = driver.findElement(By.xpath("//div[@class='flex justify-between p-5']"));
        ((org.openqa.selenium.JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", footer);
    }

    @When("User clicks on {string} link")
    public void user_clicks_on_link(String linkText) {
        WebElement link = driver.findElement(By.linkText(linkText));
        link.click();
    }

    @Then("The page title should be {string}")
    public void the_page_title_should_be(String expectedTitle) {
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title: "+actualTitle);
        System.out.println("Expected Title: "+ expectedTitle);
        Assert.assertEquals(actualTitle, expectedTitle, "Page title mismatch!");
        driver.quit();
    }
}
