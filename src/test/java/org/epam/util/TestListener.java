package org.epam.util;

import org.openqa.selenium.WebDriver;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = WebDriverSingleton.getDriver();
        if (driver != null || result.isSuccess()) {
            ScreenshotUtil.takeScreenshot(driver);
        }
    }
}