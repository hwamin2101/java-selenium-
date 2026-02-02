package session1.exercise1_3.tests;

import org.openqa.selenium.WindowType;
import org.testng.Assert;
import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_3.pages.LoginPage;
import session1.exercise1_3.pages.SuccessPage;
import session1.utils.ConfigReader;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLoginSuccessfully() {

        LoginPage loginPage = new LoginPage(driver)
                .open()
                .enterUsername(ConfigReader.get("ex3.username"))
                .enterPassword(ConfigReader.get("ex3.password"));

        SuccessPage successPage = loginPage.clickSubmit();

        Assert.assertEquals(
                successPage.getSuccessText(),
                ConfigReader.get("ex3.success.text")
        );

        String parentWindow = driver.getWindowHandle();
        String successUrl = successPage.getCurrentUrl();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(successUrl);

        SuccessPage successPageInNewTab = new SuccessPage(driver);

        Assert.assertTrue(
                successPageInNewTab.isLoggedIn(),
                "User should be logged in on the new tab"
        );

        driver.switchTo().window(parentWindow);
    }

}

