package org.epam.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtil {
    private static final Logger logger = LoggerFactory.getLogger(ScreenshotUtil.class);

    public static void takeScreenshot(WebDriver driver) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filePath = "screenshots/" + timestamp + ".png";
        try {
            FileUtils.copyFile(screenshot, new File(filePath));
            logger.info("Screenshot is saved at " + filePath);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}