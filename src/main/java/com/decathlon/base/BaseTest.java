package com.decathlon.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.testng.annotations.*;

import java.time.Duration;

public class BaseTest {
    public WebDriver driver;

    @BeforeSuite
    @Parameters("browser")
    public void setup(@Optional("chrome") String browser) {
        if(browser == "chrome"){


            //handle notifications
            ChromeOptions options = new ChromeOptions();
//            options.addArguments("--headless=new");
            options.addArguments("--disable-notifications");
            options.addArguments("--disable-geolocation");
            WebDriver driver = new ChromeDriver(options); // Initialize driver

        }
        else{
            EdgeOptions options1 = new EdgeOptions();
//            options1.addArguments("--headless=new");
            options1.addArguments("--disable-notifications");
            options1.addArguments("--disable-geolocation");
            driver = new EdgeDriver(options1);
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://www.decathlon.in/");
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}