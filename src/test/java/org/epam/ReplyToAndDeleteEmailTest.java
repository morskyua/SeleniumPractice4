package org.epam;

import org.epam.util.WebDriverSingleton;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

public class ReplyToAndDeleteEmailTest extends BaseTest {

    @AfterMethod
    void removeReply() {
        gmailPage.deleteReply();
        WebDriverSingleton.tearDown();
    }

    @Test
    void testReplyAndAssertSubject() {
        gmailPage.replyToEmail("DemoReplyText").openSentEmails();
        Assert.assertEquals(gmailPage.getEmailSubject().getText(), "DemoEmail");
    }

    @Test
    void testReplyAndAssertText() {
        String actual = gmailPage.replyToEmail("DemoReplyAssertText").openSentEmails().getFirstReplyText();
        Assert.assertEquals(actual, "DemoReplyAssertText");
    }
}
