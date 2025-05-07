package org.epam.util;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WebDriverSingleton {
    private static final Duration IMPLICIT_WAIT_DURATION = Duration.ofSeconds(5);
    private static final ThreadLocal<WebDriver> threadLocalInstance;
    private static final WebDriverManager manager;

    static {
        manager = WebDriverManager.edgedriver();
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
        if (threadLocalInstance.get().getTitle() != null) {
            threadLocalInstance.get().quit();
        }
        threadLocalInstance.remove();
    }

    private WebDriverSingleton() {
    }
}
