package com.decathlon.testng.test;

import com.decathlon.base.BaseTest;
import com.decathlon.pages.AddCart_Page;
import com.decathlon.pages.HomePage;
import com.decathlon.pages.ProductListPage;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class CartWorkflowTest extends BaseTest {
    AddCart_Page addCart;
    ProductListPage product;
    @BeforeClass
    public void pageInitialization(){
        addCart = new AddCart_Page(driver, wait);
    }

    @Test(priority = 1)
    public void validateProductDetails() throws InterruptedException {
        addCart.retrieveProductItemDetails();
    }

    @Test(priority = 2)
    public void addCartWithoutSizeSele(){
        addCart.addToCartWithoutSizeSelection();
    }



    @Test(priority = 3)
    public void addCartWtSizeSele(){
        addCart.addToCartWithSizeSelection();
    }

    @Test(priority = 4)
    public void cartPageOperation() throws InterruptedException {
        addCart.updateQuantityInCart();
    }

    @Test(priority = 5)
    public void cartPriceValidation(){
        addCart.priceChangeValidation();
    }

    @Test(priority = 6)
    public void clearCart() throws InterruptedException {
        addCart.clearCartAndVerify();
    }
}
