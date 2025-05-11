package org.epam.businessobjects;

import org.epam.pages.GmailPage;
import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;

public abstract class BaseBO {
    private static final Logger logger = LoggerFactory.getLogger(BaseBO.class);
    protected WebDriverWait wait;
    protected GmailPage gmailPage;
    protected BaseBO() {
        gmailPage = PageFactory.initElements(WebDriverSingleton.getDriver(), GmailPage.class);
        wait = new WebDriverWait(WebDriverSingleton.getDriver(), Duration.ofSeconds(5));
    }

    public void openFirstEmail() {
        logger.info("Opening first email");
        gmailPage.getFirstEmail().click();
        wait.until(ExpectedConditions.visibilityOf(gmailPage.getLastMoreMessageOptionsButton()));
    }

    public String getEmailSubject() {
        return gmailPage.getEmailSubject().getText();
    }

}
