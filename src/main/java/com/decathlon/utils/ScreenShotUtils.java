package com.decathlon.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.io.FileHandler;

import java.io.File;

public class ScreenShotUtils {
    public static void captureScreenshot(WebDriver driver, String screenshotName) {
        try {
            // Convert WebDriver object to TakeScreenshot
            TakesScreenshot ts = (TakesScreenshot) driver;
            // Capture screenshot as File object
            File source = ts.getScreenshotAs(OutputType.FILE);
            // Define destination path

            File destination = new File(System.getProperty("user.dir")+ "\\Screenshots\\" + screenshotName + ".png");
            // Copy file to destination
            FileHandler.copy(source, destination);
            System.out.println("Screenshot taken: " + screenshotName);
        } catch (Exception e) {
            System.out.println("Exception while taking screenshot: " + e.getMessage());
        }
    }
}
