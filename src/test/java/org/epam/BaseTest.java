package org.epam;

import org.epam.businessobjects.GmailLoginBO;
import org.epam.util.PropertyReader;
import org.epam.util.TestListener;
import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

@Listeners(TestListener.class)
public abstract class BaseTest {
    private final PropertyReader propertyReader = PropertyReader.getEnvProperties();
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    protected GmailLoginBO gmailLoginBO;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriver driver = WebDriverSingleton.getDriver();
        gmailLoginBO = new GmailLoginBO(driver);
        String url = propertyReader.getProperty("gmailComUrl");
        logger.info("Opening Gmail Login page: " + url);
        driver.get(url);
        String login = propertyReader.getProperty("login");
        String password = propertyReader.getProperty("password");
        logger.info("Log in with {} login and {} password", login, password);
        gmailLoginBO.login(login, password);
    }

}
