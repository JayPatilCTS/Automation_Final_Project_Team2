package com.decathlon.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductListPage {
    private WebDriver driver;
    private WebDriverWait wait;

    public ProductListPage(WebDriver driver, WebDriverWait wait){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void searchBoxInput() throws InterruptedException {
        WebElement searchBox = driver.findElement(By.xpath("//span[normalize-space()='Search for']"));
        searchBox.click();
        WebElement inputText = driver.findElement(By.xpath("//input[@type='text']"));
        inputText.sendKeys("jacket"+ Keys.ENTER);


        Thread.sleep(3000);
        WebElement resultElement = driver.findElement(By.xpath("//div[@class='my-2 md:mb-8 mb-2 text-center']"));

        // Get the full text: "Showing 315 results for Shoes For Men"
        String text = resultElement.getText();

        // Extract the number using regex
        String numberOnly = text.replaceAll("[^0-9]", "");  // removes everything except digits

        // Convert to integer if needed
        int count = Integer.parseInt(numberOnly);
        Assert.assertTrue(text.contains("jacket"),"Invalid Product get searched");
        System.out.println("WebPage Searched for Correct Product");
        System.out.println("Result count: " + count);

    }

    public void autoSearchBoxInput(String text) throws InterruptedException {
        WebElement autoSearchBox = driver.findElement(By.xpath("//span[normalize-space()='Search for']"));
        autoSearchBox.click();
        WebElement inputText = driver.findElement(By.xpath("//input[@type='text']"));
        inputText.sendKeys(text);
        Thread.sleep(2000);

        List<WebElement> optionlist = driver.findElements(By.xpath("//ul//li[@class='pr-3 text-14 cursor-pointer']"));

        for (int i = 0; i < optionlist.size(); i++) {
            if (optionlist.get(i).getText().equals("Shoes For Men")) {
                optionlist.get(i).click();
                break;
            }
        }
    }

    public void sortSearchResult(){
        WebElement sortDropdown = driver.findElement(By.xpath("//span[normalize-space()='Most Relevant']"));
        sortDropdown.click();
        driver.findElement(By.xpath("//a[normalize-space()='Price: Low to High']")).click();
    }

    public void applyCheckboxValues(){
        List<WebElement> categoryList = driver.findElements(By.xpath("//span[normalize-space()='Sport']/following-sibling::ul//li[@class='flex items-center cursor-pointer']"));

        System.out.println(categoryList.size());
        for(int i=0;i<3;i++){
            categoryList.get(i).click();
        }
    }

    public void applyPriceSlider() throws InterruptedException {

        WebElement maxSlider = driver.findElement(By.xpath("(//input[@type='range'])[2]"));

        System.out.println("Initial Max Value: " + maxSlider.getAttribute("value"));

        JavascriptExecutor js = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        js.executeScript(
                "arguments[0].value = arguments[1]; arguments[0].dispatchEvent(new Event('input'));",
                maxSlider, "5000"
        );
        System.out.println("Updated Max Value: " + maxSlider.getAttribute("value"));
    }

    public void updateSliderRange() throws InterruptedException {
        // Locate the slider range bar
        WebElement rangeBar = driver.findElement(By.cssSelector(".style_slider__range__Z1r4w"));

        // Print current style
        System.out.println("Initial Range Style: " + rangeBar.getAttribute("style"));

        // Use JavaScript to update the width to 15%
        Thread.sleep(3000);
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].style.width = '15%';", rangeBar);

        // Print updated style
        System.out.println("Updated Range Style: " + rangeBar.getAttribute("style"));
    }

}
