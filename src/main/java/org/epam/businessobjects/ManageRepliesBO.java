package org.epam.businessobjects;

import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManageRepliesBO extends BaseBO {
    private static final Logger logger = LoggerFactory.getLogger(ManageRepliesBO.class);
    public ManageRepliesBO replyToEmail(String text) {
        logger.info("Replying to email and sending the reply");
        gmailPage.getFirstEmail().click();
        gmailPage.getReplyButton().click();
        gmailPage.getReplyTextArea().sendKeys(text);
        gmailPage.getSendReplyButton().click();
        WebDriverSingleton.quickWait(ExpectedConditions.invisibilityOf(gmailPage.getSendReplyButton()), wait);
        return this;
    }

    public ManageRepliesBO openSentEmails() {
        logger.info("Opening sent emails");
        gmailPage.getSentButton().click();
        wait.until(driver1 -> gmailPage.getAdvancedSearchButton().isEnabled());
        return this;
    }

    public ManageRepliesBO deleteReply() {
        if (getReplyCount() != 0) {
            if (!gmailPage.getEmails().isEmpty()) {
                openFirstEmail();
            }
            logger.info("Deleting the reply");
            gmailPage.getLastMoreMessageOptionsButton().click();
            wait.until(ExpectedConditions.visibilityOf(gmailPage.getDeleteReplyButton()));
            gmailPage.getDeleteReplyButton().click();
            wait.until(ExpectedConditions.invisibilityOf(gmailPage.getExpandAllButton()));
        }
        return this;
    }
    public int getReplyCount() {
        return gmailPage.getMoreMessageOptions().size() - 1;
    }


}
