package org.epam.businessobjects;

import org.openqa.selenium.support.ui.ExpectedConditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkEmailBO extends BaseBO {
    private static final Logger logger = LoggerFactory.getLogger(MarkEmailBO.class);
    public MarkEmailBO markAsStarred() {
        logger.info("Marking email as starred");
        gmailPage.getFirstEmail().click();
        gmailPage.getMarkAsStarredButton().click();
        return this;
    }

    public MarkEmailBO openStarredEmails() {
        logger.info("Open starred emails");
        gmailPage.getStarredButton().click();
        wait.until(driver -> gmailPage.getFirstEmail().isDisplayed());
        return this;
    }

    public void unMark() {
        openFirstEmail();
        logger.info("UnMark the email");
        gmailPage.getEmailStarredButton().click();
        wait.until(ExpectedConditions.invisibilityOf(gmailPage.getEmailStarredButton()));
    }

    public int getStarredCount() {
        return gmailPage.getEmailSelectors().size();
    }
}
