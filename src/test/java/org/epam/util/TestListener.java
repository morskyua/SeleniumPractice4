package org.epam.util;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

public class TestListener extends ReportPortalTestNGListener {
    @Override
    public void onTestFailure(ITestResult testResult) {
        WebDriver driver = WebDriverSingleton.getDriver();
        if (driver != null) {
            ScreenshotUtil.takeScreenshot(driver);
        }
        super.onTestFailure(testResult);
    }
}