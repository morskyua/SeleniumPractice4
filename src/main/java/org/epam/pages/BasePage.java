package org.epam.pages;

import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class BasePage {
    protected final WebDriver driver = WebDriverSingleton.getDriver();
    protected final WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
}
