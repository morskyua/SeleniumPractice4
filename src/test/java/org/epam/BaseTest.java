package org.epam;

import org.epam.pages.GmailLoginPage;
import org.epam.pages.GmailPage;
import org.epam.util.PropertyReader;
import org.epam.util.WebDriverSingleton;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;

public class BaseTest {
    protected final PropertyReader propertyReader = PropertyReader.fromProperties("src/test/resources/prod.properties");
    protected GmailPage gmailPage;
    protected GmailLoginPage loginPage;

    @BeforeMethod
    public void setup() {
        WebDriver driver = WebDriverSingleton.getDriver();
        loginPage = PageFactory.initElements(driver, GmailLoginPage.class);
        gmailPage = PageFactory.initElements(driver, GmailPage.class);
        String url = propertyReader.getProperty("gmailComUrl");
        driver.get(url);
        loginPage.login(propertyReader.getProperty("login"), propertyReader.getProperty("password"));
    }

}
