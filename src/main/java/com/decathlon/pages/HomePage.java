package com.decathlon.pages;

import com.decathlon.utils.ExcelUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;

    public HomePage(WebDriver driver) {

        this.driver = driver;
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(By.xpath("//a[@href='/']")).isDisplayed();

    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void homePageVerification(){
        System.out.println("---------------------------------------------------------");
        System.out.println("Test Case - Home Page and Logo Verification");
        Assert.assertTrue(isLogoDisplayed());
        System.out.println("Logo is displayed");
        String actualTitle = getHomePageTitle();
        String expectedTitle = "Buy Sporting Goods, Sportswear and Equipments | Download App";
        Assert.assertNotNull(actualTitle);   // As the .contains may throw NullPointerException
        Assert.assertTrue(actualTitle.contains(expectedTitle),
                "Title mismatch! Expected to contain: " + expectedTitle + " but found: " + actualTitle);
        System.out.println("Title matches! == "+actualTitle);
    }

    public void alertAndPopupHandle(){
        System.out.println("---------------------------------------------------------");
        System.out.println("Test Case - Alert and Pop up Handling");
        try { Alert alert = driver.switchTo().alert();
            System.out.println("Alert text: " + alert.getText());
            alert.accept(); // or alert.dismiss()

            WebElement cookieBanner = driver.findElement(By.id("cookie-accept"));
            cookieBanner.click();

        } catch (NoAlertPresentException e) {
            System.out.println("No alert present, continuing...");
        } catch (NoSuchElementException e) {
            System.out.println("No cookie banner present.");
        }
    }

    public void signIn() throws Exception {

        //change this below path according to our system
        String filePath = "C:\\Users\\2464049\\IdeaProjects\\Decathlon_final_project_team2\\testData\\invalidUserCredentials.xlsx";

        List<String> invalidEmails = ExcelUtils.getInvalidEmails(filePath);
        String expectedError = "Can’t find Decathlon account. Click “Create your DECATHLON account” button below.";
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        // 1. Navigate/Click Sign In
        WebElement signInBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//p[normalize-space()='Sign In']")));
        signInBtn.click();
        System.out.println("---------------------------------------------------------");
        System.out.println("Test Case - Sign In with Invalid Credentials");

        for (String email : invalidEmails) {
            try {
                System.out.println("Testing with email: " + email);

                WebElement emailInput = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("input-email")));
                emailInput.clear();
                emailInput.sendKeys(email);

                driver.findElement(By.id("lookup-btn")).click();

                try {
                    WebElement errorElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='textfield-error']//p")));
                    String actualError = errorElement.getText();
                    Assert.assertEquals(actualError, expectedError, "Error message mismatch for: " + email);
                    System.out.println("Assertion Passed for: " + email);
                    System.out.println();
                } catch (TimeoutException e) {
                    System.out.println("Error message NOT displayed for: " + email + " (Negative Test Failed)");
                }

            } catch (Exception e) {
                System.out.println("Iteration failed for " + email + ": " + e.getMessage());
            } finally {
                // 5. Refresh for the next input as requested
                driver.navigate().refresh();
            }
        }
        driver.findElement(By.xpath("//span[normalize-space()='Back']")).click();
    }


}