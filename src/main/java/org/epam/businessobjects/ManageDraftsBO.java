package org.epam.businessobjects;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ManageDraftsBO extends BaseBO {
    private static final Logger logger = LoggerFactory.getLogger(ManageDraftsBO.class);

    public ManageDraftsBO createDraftMail(String address, String subject, String text) {
        logger.info("Creating draft email and saving");
        gmailPage.getComposeButton().click();
        wait.until(driver -> gmailPage.getSubjectInput().isEnabled());
        gmailPage.getRecipientsInput().sendKeys(address);
        gmailPage.getSubjectInput().sendKeys(subject);
        gmailPage.getTextInput().sendKeys(text);
        gmailPage.getCloseButton().click();
        return this;
    }

    public ManageDraftsBO openDrafts() {
        logger.info("Opening drafts");
        gmailPage.getDraftsButton().click();
        wait.until(driver -> gmailPage.getDraftsButton().getAttribute("class").contains("aiq"));
        return this;
    }

    public ManageDraftsBO deleteDraft() {
        if (getEmptyNotification().isEmpty()) {
            logger.info("Deleting draft email");
            gmailPage.getFirstEmail().click();
            wait.until(ExpectedConditions.visibilityOf(gmailPage.getDeleteDraftButton()));
            gmailPage.getDeleteDraftButton().click();
            wait.until(ExpectedConditions.visibilityOf(gmailPage.getNoDraftsNotification()));
        }
        return this;
    }

    public String getEmptyNotification() {
        wait.until(ExpectedConditions.visibilityOf(gmailPage.getNoDraftsNotification()));
        return gmailPage.getNoDraftsNotification().getText();
    }
}
