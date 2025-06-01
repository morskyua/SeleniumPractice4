package org.epam;

import org.epam.service.EmailReplyCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ReplyToAndDeleteAbstractEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ReplyToAndDeleteAbstractEmailTest.class);

    @BeforeClass
    public void initCreator() {
        emailCreator = new EmailReplyCreator();
    }

    @AfterMethod(alwaysRun = true)
    void removeReply() {
        gmailPage.deleteReply(abstractEmail);
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testReplySubject() {
        String actual = gmailPage.replyToEmail(abstractEmail)
                .openSentEmails()
                .getEmailSubjectText();
        logger.info("Verifying reply subject");
        Assert.assertEquals(actual, "DemoEmail");
    }

    @Test
    void testDeleteReply() {
        int actual = gmailPage.replyToEmail(abstractEmail)
                .openSentEmails()
                .deleteReply(abstractEmail)
                .getReplyCount();
        logger.info("Verifying that reply is deleted");
        Assert.assertEquals(actual, 0);
    }
}
