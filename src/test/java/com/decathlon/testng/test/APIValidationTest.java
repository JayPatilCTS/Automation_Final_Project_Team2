package com.decathlon.testng.test;

import com.decathlon.utils.APIValidator;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

public class APIValidationTest {
    private ChromeDriver driver;
    private APIValidator apiValidator;

    @BeforeClass
    public void setUp() {
        driver = new ChromeDriver();
        apiValidator = new APIValidator(driver);
    }

    @Test
    public void validateBackgroundRequests() throws InterruptedException {
        driver.get("https://https://www.yatra.com/");
        // Wait for background requests
        Thread.sleep(5000);

        Assert.assertTrue(apiValidator.allRequestsSuccessful(),
                "Some background requests did not return 200 OK");
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }
}

