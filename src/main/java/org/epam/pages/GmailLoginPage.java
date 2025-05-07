package org.epam.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GmailLoginPage extends BasePage {
    @FindBy(id = "identifierId")
    private WebElement loginInput;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    public WebElement getLoginInput() {
        return loginInput;
    }

    public WebElement getPasswordInput() {
        return passwordInput;
    }

    public WebElement getNextButton() {
        return nextButton;
    }

    public void login(String login, String password) {
        getLoginInput().sendKeys(login);
        getNextButton().click();
        wait.until(ExpectedConditions.visibilityOf(getPasswordInput()));
        getPasswordInput().sendKeys(password);
        getNextButton().click();
    }
}
