package com.decathlon.pages;

import org.openqa.selenium.*;
import org.testng.Assert;

public class HomePage {
    private WebDriver driver;

    // Locators
    By logo = By.xpath("//a[@href='/']");

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();

    }

    public String getHomePageTitle() {
        return driver.getTitle();
    }

    public void homePageVerification(){
        Assert.assertTrue(isLogoDisplayed());
        System.out.println("Logo is displayed");
        Assert.assertEquals(getHomePageTitle(), "Buy Sporting Goods, Sportswear and Equipments | Download App");
        System.out.println("WebPage title is verified");
    }

    public void alertAndPopupHandle(){
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

}