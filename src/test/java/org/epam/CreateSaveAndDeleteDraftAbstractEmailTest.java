package org.epam;

import org.epam.service.DraftEmailCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class CreateSaveAndDeleteDraftAbstractEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateSaveAndDeleteDraftAbstractEmailTest.class);

    public CreateSaveAndDeleteDraftAbstractEmailTest() {
        emailCreator = new DraftEmailCreator();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        gmailPage.deleteDraft();
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testDraftSubject() {
        String actual = gmailPage.createDraftMail(abstractEmail)
                .openDrafts()
                .getEmailSubject()
                .getText();
        logger.info("Verifying draft subject");
        Assert.assertEquals(actual, "demoDraftSubject");
    }

    @Test
    void testDeleteCreatedDraft() {
        String actual = gmailPage.createDraftMail(abstractEmail)
                .openDrafts()
                .deleteDraft()
                .getEmptyNotification();
        logger.info("Verifying that draft is deleted");
        Assert.assertTrue(actual.contains("You don't have any saved drafts."));
    }
}
