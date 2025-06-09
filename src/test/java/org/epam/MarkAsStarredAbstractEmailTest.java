package org.epam;

import org.epam.service.MarkedEmailCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class MarkAsStarredAbstractEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(MarkAsStarredAbstractEmailTest.class);

    public MarkAsStarredAbstractEmailTest() {
        emailCreator = new MarkedEmailCreator();
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        gmailPage.unMark(abstractEmail);
        WebDriverSingleton.tearDown();
    }

    @Test
    void testMarkAsStarred() {
        int actual = gmailPage.markAsStarred(abstractEmail)
                .openStarredEmails()
                .getStarredCount();
        logger.info("Verifying that email is marked as starred");
        Assert.assertEquals(actual, 1);
    }
}
