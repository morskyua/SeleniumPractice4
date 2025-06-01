package org.epam.pages;

import org.epam.model.AbstractEmail;
import org.epam.util.WebDriverSingleton;
import org.epam.webelements.Button;
import org.epam.webelements.Input;
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

    public Input getRecipientsInput() {
        return new Input(recipientsInput);
    }

    public Input getSubjectInput() {
        return new Input(subjectInput);
    }

    public Input getTextInput() {
        return new Input(textInput);
    }

    public Button getExpandAllButton() {
        return new Button(expandAllButton);
    }

    public Button getAdvancedSearchButton() {
        return new Button(advancedSearchButton);
    }

    public Button getStarredButton() {
        return new Button(starredButton);
    }

    public Button getMarkAsStarredButton() {
        return new Button(markAsStarredButton);
    }

    public Button getDeleteReplyButton() {
        return new Button(deleteReplyButton);
    }

    public Button getSentButton() {
        return new Button(sentButton);
    }

    public Button getSendReplyButton() {
        return new Button(sendReplyButton);
    }

    public Button getReplyButton() {
        return new Button(replyButton);
    }

    public Button getEmailStarredButton() {
        return new Button(emailStarredButton);
    }

    public Button getComposeButton() {
        return new Button(composeButton);
    }

    public Button getCloseButton() {
        return new Button(closeButton);
    }

    public Button getDraftsButton() {
        return new Button(draftsButton);
    }

    public Button getDeleteDraftButton() {
        return new Button(deleteDraftButton);
    }

    public Button getLastMoreMessageOptionsButton() {
        return new Button(moreMessageOptions.getLast());
    }

    public List<WebElement> getEmailSelectors() {
        return emailSelectors;
    }

    public List<WebElement> getMoreMessageOptions() {
        return moreMessageOptions;
    }

    public List<WebElement> getEmails() {
        return emails;
    }

    public WebElement getEmailSubject() {
        return emailSubject;
    }

    public WebElement getNoDraftsNotification() {
        return noDraftsNotification;
    }

    public WebElement getReplyTextArea() {
        return replyTextArea;
    }

    public WebElement getFirstEmail() {
        return emails.getFirst();
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

    public GmailPage createDraftMail(AbstractEmail draftAbstractEmail) {
        logger.info("Creating draft email and saving");
        getComposeButton().click();
        wait.until(driver -> getSubjectInput().isEnabled());
        getRecipientsInput().sendKeys(draftAbstractEmail.getRecipient());
        getSubjectInput().sendKeys(draftAbstractEmail.getSubject());
        getTextInput().sendKeys(draftAbstractEmail.getText());
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
            getDeleteDraftButton().waitUntilVisible(true);
            getDeleteDraftButton().click();
            wait.until(ExpectedConditions.visibilityOf(getNoDraftsNotification()));
        }
        return this;
    }

    public String getEmptyNotification() {
        return getNoDraftsNotification().getText();
    }

    public GmailPage replyToEmail(AbstractEmail targetEmail) {
        logger.info("Replying to email and sending the reply");
        getTargetEmail(targetEmail.getSubject()).click();
        getReplyButton().click();
        getReplyTextArea().sendKeys(targetEmail.getText());
        getSendReplyButton().click();
        WebDriverSingleton.quickWait(ExpectedConditions.invisibilityOf(getSendReplyButton().getWebElement()), wait);
        return this;
    }

    public GmailPage openSentEmails() {
        logger.info("Opening sent emails");
        getSentButton().click();
        wait.until(driver1 -> getAdvancedSearchButton().isEnabled());
        return this;
    }

    public GmailPage deleteReply(AbstractEmail abstractEmail) {
        if (getReplyCount() != 0) {
            if (!getEmails().isEmpty()) {
                getTargetEmail(abstractEmail.getSubject()).click();
            }
            logger.info("Deleting the reply");
            getLastMoreMessageOptionsButton().click();
            getDeleteReplyButton().waitUntilVisible(true);
            getDeleteReplyButton().click();
            getExpandAllButton().waitUntilVisible(false);
        }
        return this;
    }

    public int getReplyCount() {
        return getMoreMessageOptions().size() - 1;
    }

    public GmailPage markAsStarred(AbstractEmail abstractEmail) {
        logger.info("Marking email as starred");
        getTargetEmail(abstractEmail.getSubject()).click();
        getMarkAsStarredButton().click();
        return this;
    }

    public GmailPage openStarredEmails() {
        logger.info("Open starred emails");
        getStarredButton().click();
        wait.until(driver -> getFirstEmail().isDisplayed());
        return this;
    }

    public void unMark(AbstractEmail abstractEmail) {
        getTargetEmail(abstractEmail.getSubject()).click();
        logger.info("UnMark the email");
        getEmailStarredButton().click();
        getEmailStarredButton().waitUntilVisible(false);
    }

    public int getStarredCount() {
        return getEmailSelectors().size();
    }
}
