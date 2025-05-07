package org.epam.pages;

import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;

public class GmailPage extends BasePage {
    @FindBy(xpath = "//*[text()='Compose']")
    private WebElement composeButton;

    @FindBy(xpath = "//*[@aria-label='To recipients']")
    private WebElement recipientsInput;

    @FindBy(xpath = "//*[@aria-label='Subject']")
    private WebElement subjectInput;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement textInput;

    @FindBy(xpath = "//*[@aria-label='Save & close']")
    private WebElement closeButton;

    @FindBy(css = "[data-tooltip='Drafts']")
    private WebElement draftsButton;

    @FindBy(xpath = "//*[@role='main']//tr[1]/td[5]/div/div/div/span/span")
    private WebElement emailSubject;

    @FindBy(xpath = "//*[@role='main']//tr")
    private List<WebElement> emails;

    @FindBy(xpath = "//div[contains(@aria-label, 'Discard draft')]")
    private WebElement deleteDraftButton;

    @FindBy(xpath = "//div[@aria-label='Reply']")
    private WebElement replyButton;

    @FindBy(xpath = "//div[@aria-label='Message Body']")
    private WebElement replyTextArea;

    @FindBy(xpath = "//div[contains(@data-tooltip, 'Send')]")
    private WebElement sendReplyButton;

    @FindBy(css = "[data-tooltip='Sent']")
    private WebElement sentButton;

    @FindBy(xpath = "//*[@class='a3s aiL ']/div[1]")
    private WebElement replyEmailText;

    @FindBy(xpath = "//*[@aria-label='More message options']")
    private List<WebElement> moreMessageOptions;

    @FindBy(xpath = "//*[@role='menu']//div[text()='Delete this message']")
    private WebElement deleteReplyButton;

    @FindBy(xpath = "//div[@aria-label='Not starred'][1]")
    private WebElement markAsStarredButton;

    @FindBy(xpath = "//div[@data-tooltip='Starred']")
    private WebElement starredButton;

    @FindBy(xpath = "//div[@aria-label='Starred']")
    private WebElement emailStarredButton;

    @FindBy(xpath = "//*[@class='gmail_quote']")
    private WebElement gmailQuoteInEmail;

    @FindBy(xpath = "//span[text()='Advanced search']")
    private WebElement advancedSearchButton;

    @FindBy(xpath = "//button[@aria-label='Expand all']")
    private WebElement expandAllButton;

    public WebElement getExpandAllButton() {
        return expandAllButton;
    }

    public WebElement getAdvancedSearchButton() {
        return advancedSearchButton;
    }

    public WebElement getStarredButton() {
        return starredButton;
    }

    public WebElement getMarkAsStarredButton() {
        return markAsStarredButton;
    }

    public WebElement getMoreMessageOptions() {
        return moreMessageOptions.getLast();
    }

    public WebElement getDeleteReplyButton() {
        return deleteReplyButton;
    }

    public WebElement getReplyEmailText() {
        return replyEmailText;
    }

    public WebElement getSentButton() {
        return sentButton;
    }

    public WebElement getSendReplyButton() {
        return sendReplyButton;
    }

    public WebElement getReplyTextArea() {
        return replyTextArea;
    }

    public WebElement getReplyButton() {
        return replyButton;
    }

    public WebElement getEmailStarredButton() {
        return emailStarredButton;
    }

    public WebElement getComposeButton() {
        return composeButton;
    }

    public WebElement getRecipientsInput() {
        return recipientsInput;
    }

    public WebElement getSubjectInput() {
        return subjectInput;
    }

    public WebElement getTextInput() {
        return textInput;
    }

    public WebElement getCloseButton() {
        return closeButton;
    }

    public WebElement getDraftsButton() {
        return draftsButton;
    }

    public WebElement getFirstEmail() {
        return emails.getFirst();
    }

    public WebElement getEmailSubject() {
        return emailSubject;
    }

    public WebElement getDeleteDraftButton() {
        return deleteDraftButton;
    }

    public void createDraftMail(String address, String subject, String text) {
        getComposeButton().click();
        wait.until(driver -> getSubjectInput().isEnabled());
        getRecipientsInput().sendKeys(address);
        getSubjectInput().sendKeys(subject);
        getTextInput().sendKeys(text);
        getCloseButton().click();
    }

    public void openDrafts() {
        getDraftsButton().click();
        wait.until(driver -> getDraftsButton().getAttribute("class").contains("aiq"));
    }

    public void deleteDraft() {
        getFirstEmail().click();
        wait.until(ExpectedConditions.visibilityOf(getDeleteDraftButton()));
        getDeleteDraftButton().click();
    }

    public GmailPage replyToEmail(String text) {
        getFirstEmail().click();
        getReplyButton().click();
        getReplyTextArea().sendKeys(text);
        getSendReplyButton().click();
        WebDriverSingleton.quickWait(ExpectedConditions.invisibilityOf(getSendReplyButton()), wait);
        return this;
    }

    public GmailPage openSentEmails() {
        getSentButton().click();
        wait.until(driver1 -> getAdvancedSearchButton().isEnabled());
        return this;
    }

    public String getFirstReplyText() {
        openFirstEmail();
        return getReplyEmailText().getText();
    }

    public void deleteReply() {
        if (!emails.isEmpty()) {
            openFirstEmail();
        }
        getMoreMessageOptions().click();
        wait.until(ExpectedConditions.visibilityOf(getDeleteReplyButton()));
        getDeleteReplyButton().click();
        wait.until(ExpectedConditions.invisibilityOf(getExpandAllButton()));
    }

    public void openFirstEmail() {
        getFirstEmail().click();
        wait.until(ExpectedConditions.visibilityOf(getMoreMessageOptions()));
    }

    public void openStarredEmails() {
        getStarredButton().click();
        wait.until(driver -> getFirstEmail().isDisplayed());
    }

    public void markAsStarred() {
        getFirstEmail().click();
        getMarkAsStarredButton().click();
    }

}
