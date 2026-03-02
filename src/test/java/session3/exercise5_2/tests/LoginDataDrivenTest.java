package session3.exercise5_2.tests;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import session3.base.BaseTest;
import session3.exercise5_1.pages.DashboardPage;
import session3.exercise5_1.pages.LoginPage;
import session3.exercise5_2.dataproviders.LoginDataProvider;

public class LoginDataDrivenTest extends BaseTest {

    private static final String LOGIN_URL =
            "https://demoqa.com/login";

    @Test(
            dataProvider = "loginData",
            dataProviderClass = LoginDataProvider.class
    )
    private void executeLoginTest(String username,
                                  String password,
                                  boolean expected) {

        driver.get(LOGIN_URL);

        LoginPage loginPage = new LoginPage(driver);

        try {

            DashboardPage dashboard =
                    loginPage
                            .enterUsername(username)
                            .enterPassword(password)
                            .clickLogin();

            String actualUser =
                    dashboard.getLoggedInUsername();

            boolean actual =
                    actualUser.equals(username);

            Assert.assertEquals(actual, expected,
                    "Login result mismatch for user: "
                            + username);

        } catch (Exception e) {

            if (expected) {
                Assert.fail("Expected login success but failed.");
            }
        }
    }
}