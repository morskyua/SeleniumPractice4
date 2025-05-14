package org.epam;

import org.epam.model.Email;
import org.epam.service.EmailCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MarkAsStarredEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(MarkAsStarredEmailTest.class);
    private final Email email = EmailCreator.targetEmail();
    @AfterMethod(alwaysRun = true)
    void tearDown() {
        gmailPage.unMark(email);
        WebDriverSingleton.tearDown();
    }

    @Test
    void testMarkAsStarred() {
        int actual = gmailPage.markAsStarred(email)
                .openStarredEmails()
                .getStarredCount();
        logger.info("Verifying that email is marked as starred");
        Assert.assertEquals(actual, 1);
    }
}
