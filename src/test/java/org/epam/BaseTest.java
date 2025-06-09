package org.epam;

import org.epam.model.AbstractEmail;
import org.epam.model.User;
import org.epam.pages.GmailLoginPage;
import org.epam.pages.GmailPage;
import org.epam.service.EmailCreator;
import org.epam.service.UserCreator;
import org.epam.util.PropertyReader;
import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;

public abstract class BaseTest {
    private static final Logger logger = LoggerFactory.getLogger(BaseTest.class);
    private final PropertyReader propertyReader = PropertyReader.getEnvProperties();
    protected GmailLoginPage gmailLoginPage;
    protected AbstractEmail abstractEmail;
    protected EmailCreator emailCreator;
    protected GmailPage gmailPage;

    @BeforeMethod(alwaysRun = true)
    public void setup() {
        WebDriver driver = WebDriverSingleton.getDriver();
        logger.debug("Initializing elements");
        gmailLoginPage = PageFactory.initElements(driver, GmailLoginPage.class);
        gmailPage = PageFactory.initElements(driver, GmailPage.class);
        abstractEmail = emailCreator.createEmail();
        String url = propertyReader.getProperty("gmailComUrl");
        logger.info("Opening Gmail Login page: " + url);
        driver.get(url);
        User user = UserCreator.withCredentialsFromProperty();
        logger.info("Log in with {} login and {} password", user.getUsername(), user.getPassword());
        gmailLoginPage.login(user);
    }

}
