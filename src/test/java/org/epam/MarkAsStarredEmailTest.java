package org.epam;

import org.epam.businessobjects.MarkEmailBO;
import org.epam.util.WebDriverSingleton;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MarkAsStarredEmailTest extends BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(MarkAsStarredEmailTest.class);
    private MarkEmailBO markEmailBO;

    @BeforeMethod(alwaysRun = true)
    void setUp() {
        markEmailBO = new MarkEmailBO();
    }

    @AfterMethod(alwaysRun = true)
    void tearDown() {
        markEmailBO.unMark();
        WebDriverSingleton.tearDown();
    }

    @Test
    void testMarkAsStarred() {
        int actual = markEmailBO.markAsStarred()
                .openStarredEmails()
                .getStarredCount();
        logger.info("Verifying that email is marked as starred");
        Assert.assertEquals(actual, 1);
    }
}
