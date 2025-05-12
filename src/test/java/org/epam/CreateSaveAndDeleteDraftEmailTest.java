package org.epam;

import org.epam.businessobjects.ManageDraftsBO;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class CreateSaveAndDeleteDraftEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(CreateSaveAndDeleteDraftEmailTest.class);
    private ManageDraftsBO manageDraftsBO;

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        manageDraftsBO = new ManageDraftsBO();
    }

    @AfterMethod(alwaysRun = true)
    public void tearDown() {
        manageDraftsBO.deleteDraft();
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testDraftSubject() {
        String actual = manageDraftsBO.createDraftMail("demoAddress", "demoDraftSubject", "demoText")
                .openDrafts()
                .getEmailSubject();
        logger.info("Verifying draft subject");
        Assert.assertEquals(actual, "demoDraftSubject");
    }

    @Test
    void testDeleteCreatedDraft() {
        String actual = manageDraftsBO.createDraftMail("demoAddress", "demoDraftSubject", "demoText")
                .openDrafts()
                .deleteDraft()
                .getEmptyNotification();
        logger.info("Verifying that draft is deleted");
        Assert.assertTrue(actual.contains("You don't have any saved drafts."));
    }
}
