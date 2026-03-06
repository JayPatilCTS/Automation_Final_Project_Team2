package com.decathlon.testng.test;

import com.decathlon.base.BaseTest;
import com.decathlon.pages.HomePage;
import com.decathlon.pages.ProductListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProductSearchTest extends BaseTest {
    ProductListPage product;

    @BeforeClass
    public void pageInitialization(){
        product = new ProductListPage(driver, wait);
    }

    @Test(priority = 1)
    public void searchBoxFunction() throws InterruptedException {
        product.searchBoxInput();
    }

    @Test(priority = 2, dependsOnMethods = {"searchBoxFunction"})
    public void autoSearchBoxFunction() throws InterruptedException {
        product.autoSearchBoxInput("shoe");
    }

    @Test(priority = 3, dependsOnMethods = {"searchBoxFunction","autoSearchBoxFunction"})
    public void sortProductSearchResult() throws InterruptedException {
        product.sortSearchResult();
    }

    @Test (priority = 4, dependsOnMethods = {"autoSearchBoxFunction"})
    public void applyCategoryFilterCheckbox(){
        product.applyCheckboxValues();
    }

    @Test(priority = 5, dependsOnMethods = {"autoSearchBoxFunction"})
    public void applyPrice() throws InterruptedException {
        product.applyPriceSlider();
    }

}