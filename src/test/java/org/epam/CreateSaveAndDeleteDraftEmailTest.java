package org.epam;

import org.epam.util.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.*;

public class CreateSaveAndDeleteDraftEmailTest extends BaseTest {

    @AfterMethod
    public void tearDown() {
        gmailPage.deleteDraft();
        WebDriverSingleton.tearDown();
    }

    @Test
    void testCreateDraftEmailAndCheckSubject() {
        gmailPage.createDraftMail("demoAddress", "demoDraftSubject", "demoText");
        gmailPage.openDrafts();
        Assert.assertEquals(gmailPage.getEmailSubject().getText(), "demoDraftSubject");
    }

    @Test
    void testCreateDraftEmailAndCheckText() {
        gmailPage.createDraftMail("demoAddress", "demoDraftSubject", "demoText");
        gmailPage.openDrafts();
        Assert.assertTrue(gmailPage.getFirstEmail().getText().contains("demoDraftSubject"));
    }
}
