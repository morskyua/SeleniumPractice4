package org.epam.webelements;

import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public abstract class ElementDecorator {
    protected final WebDriverWait wait = new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(5));
    protected final WebElement webElement;

    protected ElementDecorator(WebElement webElement) {
        this.webElement = webElement;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public ElementDecorator waitUntilVisible(boolean isVisible) {
        if (isVisible) {
            wait.until(ExpectedConditions.visibilityOf(webElement));
        } else {
            wait.until(ExpectedConditions.invisibilityOf(webElement));
        }
        return this;
    }

}
