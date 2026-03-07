package session3.exercise5_1.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import session3.base.BaseTest;
import session3.config.ConfigReader;
import session3.exercise5_1.dataproviders.LoginDataProvider;
import session3.exercise5_1.pages.DashboardPage;
import session3.exercise5_1.pages.LoginPage;

public class LoginDataDrivenTest extends BaseTest {

    @Test(dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class)
    public void loginWithMultipleData(String username, String password) {
        driver.get(ConfigReader.get("baseUrl"));

        DashboardPage dashboard = new LoginPage(driver)
                .enterUsername(username)
                .enterPassword(password)
                .clickLogin();

        if (driver.getCurrentUrl().contains("profile")) {

            Assert.assertEquals(dashboard.getHeaderText(), "Profile");
        } else {

            Assert.assertTrue(driver.getCurrentUrl().contains("login"),
                    "Should stay on login page if credentials invalid");
        }
    }
}