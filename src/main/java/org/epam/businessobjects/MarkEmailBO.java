package org.epam.businessobjects;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MarkEmailBO extends BaseBO {
    private static final Logger logger = LoggerFactory.getLogger(MarkEmailBO.class);
    public int getStarredCount() {
        return gmailPage.getEmailSelectors().size();
    }

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
        logger.info("UnMark the email");
        openFirstEmail();
        gmailPage.getEmailStarredButton().click();
    }
}
