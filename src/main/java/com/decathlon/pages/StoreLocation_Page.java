package com.decathlon.pages;

import com.aventstack.extentreports.reporter.configuration.Theme;
import com.decathlon.utils.ScreenShotUtils;
import org.apache.commons.compress.utils.OsgiUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class StoreLocation_Page {
    private WebDriver driver;
    private WebDriverWait wait;

    public StoreLocation_Page(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void navigateKidCollectionInHoverMenu(){
        driver.findElement(By.xpath("//div[@class='flex items-center w-1/2 md:w-auto']//button//div")).click();
        WebElement moveToKidSection = driver.findElement(By.xpath("//div[normalize-space()='Kids Collection']"));
        Actions ac = new Actions(driver);
        ac.click(moveToKidSection).build().perform();

        List<WebElement> kidsCollectionList = driver.findElements(By.xpath("//div[@class='w-1/5 pt-5']//a"));
        System.out.println("Kids Collection List: ");
        for(WebElement wb : kidsCollectionList){
            System.out.println(wb.getText());
        }
    }

    public void locateMyStoreAndVerify(){
        try {
            driver.findElement(By.xpath("//button[@class='flex items-center']//div")).click();
            driver.findElement(By.xpath("//p[normalize-space()='My Store']")).click();
        } catch (NoSuchElementException e){
            System.out.println("Not Able to Locate");
        }
    }

    public void searchStoreLocation(String city) throws InterruptedException {
        driver.findElement(By.xpath("//input[@placeholder='Enter City or State']")).sendKeys(city);
        System.out.println("before list");
        List<WebElement> locationList = driver.findElements(By.xpath("//div[@class=\"pt-2 pb-0 px-2 tCnfmr text-16 max-h-52 overflow-auto \"]//div"));
        for (WebElement ll:locationList){
            if(ll.getText().equals("Chennai, OMR MARINA MALL")) {
                System.out.println("select correct");
                ll.click();
                break;
            }

        }
        Thread.sleep(2000);
        ScreenShotUtils.captureScreenshot(driver, "LocationVerification");
        String locationAddress = driver.findElement(By.xpath("//div[@class=\"store-popup-info-image-detail-elem-class mt-2 text-grey-600 \"]//div[@class='store-popup-info-image-detail-elem-detail-class']")).getText();
        System.out.println("\nAddress of Location:");
        System.out.println(locationAddress);
    }

    public void changeDeliveryPincode(String newPinCode) throws InterruptedException {
        driver.findElement(By.xpath("//button[normalize-space()='Change']")).click();
        WebElement pincodeBox = driver.findElement(By.xpath("//input[@placeholder='Pincode']"));
        pincodeBox.clear();
        pincodeBox.sendKeys(newPinCode);
        driver.findElement(By.xpath("//button[normalize-space()='APPLY']")).click();

        Thread.sleep(3000);
        String updatedPinCode = driver.findElement(By.xpath("//div[@class=\"flex justify-center\"]//span")).getText();
        Assert.assertEquals(updatedPinCode, newPinCode );
        System.out.println("PinCode change successfully");


    }
}
