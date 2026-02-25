package com.decathlon.testng.test;

import com.decathlon.base.BaseTest;
import com.decathlon.pages.HomePage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ProductSearchTest extends BaseTest {
    HomePage home;



    @Test
    public void pageTitleAndLogoVerification(){
        home.homePageVerification();
    }
}