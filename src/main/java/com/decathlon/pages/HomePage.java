package com.decathlon.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

}