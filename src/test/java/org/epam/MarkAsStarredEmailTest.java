package org.epam;

import org.epam.util.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MarkAsStarredEmailTest extends BaseTest {

    @AfterMethod
    void tearDown() {
        gmailPage.getEmailStarredButton().click();
        WebDriverSingleton.tearDown();
    }

    @Test
    void testMarkAndSave() {
        gmailPage.markAsStarred();
        gmailPage.openStarredEmails();
        gmailPage.openFirstEmail();
        Assert.assertTrue(gmailPage.getEmailStarredButton().isEnabled());
    }
}
