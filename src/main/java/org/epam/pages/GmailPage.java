package org.epam.pages;

import org.epam.model.Email;
import org.epam.model.EmailReply;
import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class GmailPage extends BasePage {
    private static final Logger logger = LoggerFactory.getLogger(GmailPage.class);
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

    @FindBy(xpath = "//*[@role='main']//td")
    private WebElement noDraftsNotification;

    @FindBy(xpath = "//*[@role='main']//tr[@role='row']//*[@role='link']//span[@data-thread-id]")
    private List<WebElement> emailsSubjects;
    @FindBy(xpath = "//*[@role='main']//tr[@role='row']")
    private List<WebElement> emails;
    @FindBy(xpath = "//*[@role='main']//td[@data-tooltip='Select']")
    private List<WebElement> emailSelectors;

    public List<WebElement> getEmailSelectors() {
        return emailSelectors;
    }

    public List<WebElement> getMoreMessageOptions() {
        return moreMessageOptions;
    }

    public WebElement getNoDraftsNotification() {
        return noDraftsNotification;
    }

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

    public WebElement getDeleteReplyButton() {
        return deleteReplyButton;
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

    public WebElement getEmailSubject() {
        return emailSubject;
    }

    public WebElement getDeleteDraftButton() {
        return deleteDraftButton;
    }

    public WebElement getFirstEmail() {
        return emails.getFirst();
    }

    public WebElement getLastMoreMessageOptionsButton() {
        return moreMessageOptions.getLast();
    }

    public List<WebElement> getEmails() {
        return emails;
    }

    public String getEmailSubjectText() {
        return emailSubject.getText();
    }

    private WebElement getTargetEmail(String subject) {
        for (WebElement webElement : emailsSubjects) {
            if (webElement.getText().contains(subject)) {
                logger.debug(webElement.getText() + "needed email");
                return webElement;
            }
        }
        logger.error("Email with subject '%s' is not found".formatted(subject));
        return null;
    }

    public GmailPage createDraftMail(Email draftEmail) {
        logger.info("Creating draft email and saving");
        getComposeButton().click();
        wait.until(driver -> getSubjectInput().isEnabled());
        getRecipientsInput().sendKeys(draftEmail.getRecipient());
        getSubjectInput().sendKeys(draftEmail.getSubject());
        getTextInput().sendKeys(draftEmail.getDraftText());
        getCloseButton().click();
        return this;
    }

    public GmailPage openDrafts() {
        logger.info("Opening drafts");
        getDraftsButton().click();
        wait.until(driver -> getDraftsButton().getAttribute("class").contains("aiq"));
        return this;
    }

    public GmailPage deleteDraft() {
        if (getEmptyNotification().isEmpty()) {
            logger.info("Deleting draft email");
            getFirstEmail().click();
            wait.until(ExpectedConditions.visibilityOf(getDeleteDraftButton()));
            getDeleteDraftButton().click();
            wait.until(ExpectedConditions.visibilityOf(getNoDraftsNotification()));
        }
        return this;
    }

    public String getEmptyNotification() {
        return getNoDraftsNotification().getText();
    }

    public GmailPage replyToEmail(EmailReply targetEmail) {
        logger.info("Replying to email and sending the reply");
        getTargetEmail(targetEmail.getTarget().getSubject()).click();
        getReplyButton().click();
        getReplyTextArea().sendKeys(targetEmail.getText());
        getSendReplyButton().click();
        WebDriverSingleton.quickWait(ExpectedConditions.invisibilityOf(getSendReplyButton()), wait);
        return this;
    }

    public GmailPage openSentEmails() {
        logger.info("Opening sent emails");
        getSentButton().click();
        wait.until(driver1 -> getAdvancedSearchButton().isEnabled());
        return this;
    }

    public GmailPage deleteReply(Email email) {
        if (getReplyCount() != 0) {
            if (!getEmails().isEmpty()) {
                getTargetEmail(email.getSubject()).click();
            }
            logger.info("Deleting the reply");
            getLastMoreMessageOptionsButton().click();
            wait.until(ExpectedConditions.visibilityOf(getDeleteReplyButton()));
            getDeleteReplyButton().click();
            wait.until(ExpectedConditions.invisibilityOf(getExpandAllButton()));
        }
        return this;
    }

    public int getReplyCount() {
        return getMoreMessageOptions().size() - 1;
    }

    public GmailPage markAsStarred(Email email) {
        logger.info("Marking email as starred");
        getTargetEmail(email.getSubject()).click();
        getMarkAsStarredButton().click();
        return this;
    }

    public GmailPage openStarredEmails() {
        logger.info("Open starred emails");
        getStarredButton().click();
        wait.until(driver -> getFirstEmail().isDisplayed());
        return this;
    }

    public void unMark(Email email) {
        getTargetEmail(email.getSubject()).click();
        logger.info("UnMark the email");
        getEmailStarredButton().click();
        wait.until(ExpectedConditions.invisibilityOf(getEmailStarredButton()));
    }

    public int getStarredCount() {
        return getEmailSelectors().size();
    }
}
