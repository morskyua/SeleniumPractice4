package org.epam.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.SystemProperties;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public class WebDriverSingleton {
    private static final Logger logger = LoggerFactory.getLogger(WebDriverSingleton.class);
    private static final Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
    private static final ThreadLocal<WebDriver> threadLocalInstance;
    private static final WebDriverManager manager;

    static {
        String browser = SystemProperties.getProperty("browser");
        switch (browser) {
            case null:
            case "edge":
                manager = WebDriverManager.edgedriver();
                break;
            case "firefox":
                manager = WebDriverManager.firefoxdriver();
                break;
            case "chrome":
                manager = WebDriverManager.chromedriver();
                break;
            default:
                throw new IllegalArgumentException(browser + " is not supported");
        }
        manager.setup();
        threadLocalInstance = ThreadLocal.withInitial(WebDriverSingleton::createWebDriver);
    }

    public static void quickWait(ExpectedCondition<Boolean> isTrue, WebDriverWait wait) {
        getDriver().manage().timeouts().implicitlyWait(Duration.ZERO);
        wait.until(isTrue);
        getDriver().manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
    }

    public static WebDriver getDriver() {
        if (threadLocalInstance.get() == null) {
            logger.info("Creating a driver");
            threadLocalInstance.set(createWebDriver());
        }
        return threadLocalInstance.get();
    }

    private static WebDriver createWebDriver() {
        WebDriver driver = manager.create();
        driver.manage().timeouts().implicitlyWait(IMPLICIT_WAIT_DURATION);
        driver.manage().window().maximize();
        return driver;
    }

    public static void tearDown() {
        logger.info("Closing a page");
        if (threadLocalInstance.get().getTitle() != null) {
            threadLocalInstance.get().quit();
        }
        logger.info("Removing driver");
        threadLocalInstance.remove();
    }

    private WebDriverSingleton() {
    }
}
