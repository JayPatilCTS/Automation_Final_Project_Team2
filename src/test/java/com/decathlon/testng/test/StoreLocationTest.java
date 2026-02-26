package com.decathlon.testng.test;

import com.decathlon.base.BaseTest;
import com.decathlon.pages.StoreLocation_Page;
import org.apache.hc.core5.reactor.Command;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class StoreLocationTest extends BaseTest {
    StoreLocation_Page store;

    @BeforeClass
    public void pageInitialization(){
        store = new StoreLocation_Page(driver, wait);
    }

    @Test(priority = 1)
    public void extractKidCollection(){
        store.navigateKidCollectionInHoverMenu();
    }

    @Test(priority = 2)
    public void myStoreFunction(){
        store.locateMyStoreAndVerify();
    }

    @Test(priority = 3)
    public void storeLocation() throws InterruptedException {
        store.searchStoreLocation("Chennai");
    }

    @Test(priority = 4)
    public void deliveryLocation() throws InterruptedException {
        store.changeDeliveryPincode("603103");
    }
}
