package com.decathlon.testng.test;

import com.decathlon.base.BaseTest;
import com.decathlon.pages.HomePage;
import com.decathlon.pages.ProductListPage;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

public class LoginFunctionalTest extends BaseTest {

    HomePage home;
    @BeforeClass
    public void pageInitiation(){
        home = new HomePage(driver);
    }

    @Test(priority = 1)
    public void pageTitleAndLogoVerification(){
        home.homePageVerification();
    }

    @Test(priority = 2)
    public void alertHandling(){
        home.alertAndPopupHandle();
    }

    @Test(priority = 3)
    public void Negative_Login() throws Exception {
        home.signIn();
    }

}
