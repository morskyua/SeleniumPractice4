package org.epam;

import org.epam.businessobjects.ManageRepliesBO;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ReplyToAndDeleteEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(ReplyToAndDeleteEmailTest.class);
    private ManageRepliesBO manageRepliesBO;
    @BeforeMethod(alwaysRun = true)
    void setUp() {
        manageRepliesBO = new ManageRepliesBO();
    }

    @AfterMethod(alwaysRun = true)
    void removeReply() {
        manageRepliesBO.deleteReply();
        WebDriverSingleton.tearDown();
    }

    @Test(groups = "smoke")
    void testReplySubject() {
        String actual = manageRepliesBO.replyToEmail("DemoReplyAssertText")
                .openSentEmails()
                .getEmailSubject();
        logger.info("Verifying reply subject");
        Assert.assertEquals(actual, "DemoEmail");
    }

    @Test
    void testDeleteReply() {
        int actual = manageRepliesBO.replyToEmail("DemoReplyAssertText")
                .openSentEmails()
                .deleteReply()
                .getReplyCount();
        logger.info("Verifying that reply is deleted");
        Assert.assertEquals(actual, 0);
    }
}
