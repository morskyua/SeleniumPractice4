package org.epam;

import org.epam.model.Email;
import org.epam.service.EmailCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateSaveAndDeleteDraftEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateSaveAndDeleteDraftEmailTest.class);
    private final Email draftEmail = EmailCreator.createDraft();
    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        gmailPage.deleteDraft();
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testDraftSubject() {
        String actual = gmailPage.createDraftMail(draftEmail)
                .openDrafts()
                .getEmailSubject()
                .getText();
        logger.info("Verifying draft subject");
        Assert.assertEquals(actual, "demoDraftSubject");
    }

    @Test
    void testDeleteCreatedDraft() {
        String actual = gmailPage.createDraftMail(draftEmail)
                .openDrafts()
                .deleteDraft()
                .getEmptyNotification();
        logger.info("Verifying that draft is deleted");
        Assert.assertTrue(actual.contains("You don't have any saved drafts."));
    }
}
