package org.epam;

import org.epam.model.EmailReply;
import org.epam.service.EmailCreator;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ReplyToAndDeleteEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ReplyToAndDeleteEmailTest.class);
    private final EmailReply emailReply = new EmailReply(EmailCreator.targetEmail(), "DemoReplyAssertText");
    @AfterMethod(alwaysRun = true)
    void removeReply() {
        gmailPage.deleteReply(emailReply.getTarget());
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testReplySubject() {
        String actual = gmailPage.replyToEmail(emailReply)
                .openSentEmails()
                .getEmailSubjectText();
        logger.info("Verifying reply subject");
        Assert.assertEquals(actual, "DemoEmail");
    }

    @Test
    void testDeleteReply() {
        int actual = gmailPage.replyToEmail(emailReply)
                .openSentEmails()
                .deleteReply(emailReply.getTarget())
                .getReplyCount();
        logger.info("Verifying that reply is deleted");
        Assert.assertEquals(actual, 0);
    }
}
