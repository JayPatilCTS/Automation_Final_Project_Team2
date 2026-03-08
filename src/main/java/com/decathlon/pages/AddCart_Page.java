package com.decathlon.pages;

import com.decathlon.utils.ScreenShotUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.sql.SQLOutput;
import java.time.Duration;
import java.util.List;

public class AddCart_Page extends ProductListPage {
    private final WebDriverWait wait;
    private final WebDriver driver;

    public AddCart_Page(WebDriver driver, WebDriverWait wait){
        super(driver, wait);
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }


    public void retrieveProductItemDetails() throws InterruptedException {
        autoSearchBoxInput("shoe");
        sortSearchResult();
        applyCheckboxValues();
        int productCount = driver.findElements(By.xpath("//ul[@class='ais-InfiniteHits-list']//li")).size();

        for (int i = 0; i < productCount; i++) {
            try {
                // Always re-fetch product list after navigation
                List<WebElement> currentList = driver.findElements(
                        By.xpath("//ul[@class='ais-InfiniteHits-list']//li")
                );

                if (i >= currentList.size()) break;

                WebElement currentProduct = currentList.get(i);
                List<WebElement> tagImage = currentProduct.findElements(
                        By.xpath(".//img[@class='h-5 w-auto']")
                );

                // Only proceed if product has the specific tag
                if (!tagImage.isEmpty()) {
                    System.out.println("Product with tag found at index: " + i);

                    ((JavascriptExecutor) driver).executeScript(
                            "arguments[0].scrollIntoView(true);", currentProduct
                    );

                    currentProduct.click();

                    try {
                        String noSizeAva = driver.findElement(
                                By.xpath("//div[normalize-space()='No size available']")
                        ).getText();

                        Assert.assertEquals(noSizeAva, "No size available");
                        System.out.println("Cannot add product to cart --> No size available");

                        // Go back to previous page and continue with next product
                        driver.navigate().back();

                        // Optional: wait for product list to reload
                        Thread.sleep(2000);

                        continue;

                    } catch (NoSuchElementException e) {
                        System.out.println("Product size is available.");
                        // Stop loop once a valid product is found
                        break;
                    }
                }
            } catch (StaleElementReferenceException e) {
                i--; // retry same index
                System.out.println("Encountered stale element, retrying index: " + (i + 1));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        try {
            WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(
                    By.xpath("//div[@class='clearfix']//div[@role='button']")));

            System.out.println("Pop-up detected.");
            popUp.click();
        } catch (TimeoutException e) {
            System.out.println("Pop-up did not appear within 3 seconds. Proceeding with test...");
        }
        String currentTitile = driver.getTitle();
        System.out.println("Product Title is!!! -- "+currentTitile);
        System.out.println("Previous Price of Product --> "+driver.findElement(By.xpath("//div[@class='flex items-center']//span[3]")).getText());
        System.out.println("Current Price of Product --> "+driver.findElement(By.xpath("//div[@class='flex items-center']//span[1]")).getText());
    }

    public void addToCartWithoutSizeSelection(){
        driver.findElement(By.xpath("//span[normalize-space()='ADD TO CART']")).click();
        try{
            String addCartErrorText = driver.findElement(By.xpath("//div[normalize-space()='Please select a size.']")).getText();
            Assert.assertEquals(addCartErrorText, "Please select a size.");
            System.out.println("Can not add product to cart --> Please select a size.");
        } catch (NoSuchElementException e){
            System.out.println("Product Can be Add to Cart");
        }
    }

    public void addToCartWithSizeSelection(){
        List<WebElement> sizeList = driver.findElements(By.xpath("//div[@id='size-select-container']//div[@class='flex flex-col mr-1 mb-2 md:w-auto']"));

        for (WebElement sl:sizeList){
            if(sl.findElement(By.xpath("//div//div")).isEnabled()) {
                sl.click();
                System.out.println("Product Size Is Available And Clicked");
                break;
            }
        }
        driver.findElement(By.xpath("//span[normalize-space()='ADD TO CART']")).click();
    }

    public void updateQuantityInCart() throws InterruptedException {
        WebElement goToCart = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[normalize-space()='GO TO CART']")));
        goToCart.click();

        Thread.sleep(3000);
        String beforePrice = driver.findElement(By.xpath("//div[@class=\"text-sm md:text-xl\"]//span")).getText();
        driver.findElement(By.xpath("//div[@data-test-id='product-card-content']//div[@data-test-id='desktop-size-qty-controls']//div[@data-test-id=\"qty-button-container\"]//div//button[2]")).click();
        try{
            System.out.println("Product Price Before Update --> "+beforePrice);
            String afterPrice = driver.findElement(By.xpath("//div[@class=\"text-sm md:text-xl\"]//span")).getText();
            System.out.println("Product Price After Update --> "+afterPrice);
            Assert.assertNotEquals(beforePrice, afterPrice);
            System.out.println("Product Quantity Updated Successfully!!!!!");
        } catch (StaleElementReferenceException e){
            System.out.println("Element Not Found");
        }
    }

    public void priceChangeValidation(){
        try{
            String StrQTY = driver.findElement(By.xpath("//div[@data-test-id='product-card-content']//div[@data-test-id='desktop-size-qty-controls']//div[@data-test-id=\"qty-button-container\"]//div//span")).getText();
            String totalPrice = driver.findElement(By.xpath("//div[@class=\"text-sm md:text-xl\"]//span")).getText();
            String numericString = totalPrice.replaceAll("[^0-9]", "");
            int afterPrice = Integer.parseInt(numericString);
            int qty = Integer.parseInt(StrQTY);

            int beforePrice = afterPrice / qty;

            System.out.println("Product Price Before Update --> "+beforePrice);
            System.out.println("Product Price After Update --> "+afterPrice);
        } catch (StaleElementReferenceException e){
            System.out.println("Element Not Found");
        }
    }


    public void clearCartAndVerify() throws InterruptedException {
        Thread.sleep(5000);
        driver.findElement(By.xpath("//*[@data-test-id='button:delete-button']")).click();
        try{
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test-id=\"product-card-container\"]//dialog[@data-test-id=\"popup:Delete Item\"]")));
            driver.findElement(By.xpath("//div[@data-test-id=\"product-card-container\"]//dialog[@data-test-id=\"popup:Delete Item\"]//div//div//button[1]")).click();
            Thread.sleep(2000);
            String emptyCart = driver.findElement(By.xpath("//p[normalize-space()='Missing your cart?']")).getText();
            Assert.assertEquals(emptyCart, "Missing your cart?");
            System.out.println("Product Deleted Successfully -- Now Cart is Empty");

            ScreenShotUtils.captureScreenshot(driver, "EmptyCartValidation");
            Thread.sleep(2000);
            driver.findElement(By.xpath("//span[normalize-space()='Back to Shop']")).click();
        } catch (TimeoutException e){
            System.out.println("Delete Pop-up NOT Appeared --- Unable To DELETE");
        }
    }

}
