package session3.exercise5_1.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import session3.base.BaseTest;
import session3.config.ConfigReader;
import session3.exercise5_1.pages.DashboardPage;
import session3.exercise5_1.pages.LoginPage;

public class LoginFluentTest extends BaseTest {

    @Test
    public void loginSuccessfullyWithFluentAPI() {
        driver.get(ConfigReader.get("baseUrl"));

        DashboardPage dashboard = new LoginPage(driver)
                .enterUsername(ConfigReader.get("username"))
                .enterPassword(ConfigReader.get("password"))
                .clickLogin();

        Assert.assertEquals(dashboard.getHeaderText(), "Books :",
                "Header is not Books!");

        Assert.assertEquals(dashboard.getLoggedInUsername(),
                ConfigReader.get("username"),
                "Logged in username does not match!");
    }
}
