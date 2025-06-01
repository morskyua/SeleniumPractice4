package org.epam.webelements;

import org.openqa.selenium.WebElement;

public class Input extends ElementDecorator {
    public Input(WebElement webElement) {
        super(webElement);
    }

    public void sendKeys(String recipient) {
        webElement.sendKeys(recipient);
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }
}
