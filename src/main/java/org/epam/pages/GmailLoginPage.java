package org.epam.pages;

import org.epam.model.User;
import org.epam.webelements.Button;
import org.epam.webelements.Input;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class GmailLoginPage extends BasePage {
    @FindBy(id = "identifierId")
    private WebElement loginInput;

    @FindBy(xpath = "//span[text()='Next']")
    private WebElement nextButton;

    @FindBy(xpath = "//input[@type='password']")
    private WebElement passwordInput;

    public Input getLoginInput() {
        return new Input(loginInput);
    }

    public Input getPasswordInput() {
        return new Input(passwordInput);
    }

    public Button getNextButton() {
        return new Button(nextButton);
    }

    public void login(User user) {
        getLoginInput().sendKeys(user.getUsername());
        getNextButton().click();
        getPasswordInput().waitUntilVisible(true);
        getPasswordInput().sendKeys(user.getPassword());
        getNextButton().click();
    }
}
