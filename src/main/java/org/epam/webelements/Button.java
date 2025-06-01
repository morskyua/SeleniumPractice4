package org.epam.webelements;

import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Button extends ElementDecorator {
    private static final Logger logger = LoggerFactory.getLogger(Button.class);

    public Button(WebElement button) {
        super(button);
    }

    public void click() {
        logger.info("Clicking button");
        webElement.click();
    }

    public String getAttribute(String aClass) {
        return webElement.getAttribute(aClass);
    }

    public boolean isEnabled() {
        return webElement.isEnabled();
    }
}
