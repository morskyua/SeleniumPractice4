package org.epam.businessobjects;

import org.epam.pages.GmailLoginPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GmailLoginBO {
    private final GmailLoginPage gmailLoginPage;
    private final WebDriverWait wait;

    public GmailLoginBO(WebDriver driver) {
        gmailLoginPage = PageFactory.initElements(driver, GmailLoginPage.class);
        wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    public void login(String login, String password) {
        gmailLoginPage.getLoginInput().sendKeys(login);
        gmailLoginPage.getNextButton().click();
        wait.until(ExpectedConditions.visibilityOf(gmailLoginPage.getPasswordInput()));
        gmailLoginPage.getPasswordInput().sendKeys(password);
        gmailLoginPage.getNextButton().click();
    }
}
