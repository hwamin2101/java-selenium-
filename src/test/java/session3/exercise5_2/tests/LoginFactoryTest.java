package session3.exercise5_2.tests;

import org.testng.Assert;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;
import session3.base.BaseTest;
import session3.exercise5_1.pages.DashboardPage;
import session3.exercise5_1.pages.LoginPage;
import session3.exercise5_2.dataproviders.LoginDataProvider;

public class LoginFactoryTest extends BaseTest {

    private String username;
    private String password;
    private boolean expected;

    public LoginFactoryTest(String username,
                            String password,
                            boolean expected) {
        this.username = username;
        this.password = password;
        this.expected = expected;
    }

    @Factory
    public static Object[] createInstances() {

        Object[][] data =
                LoginDataProvider.getLoginData();

        Object[] tests =
                new Object[data.length];

        for (int i = 0; i < data.length; i++) {

            tests[i] = new LoginFactoryTest(
                    (String) data[i][0],
                    (String) data[i][1],
                    (Boolean) data[i][2]
            );
        }

        return tests;
    }

    @Test
    public void testLoginFactory() {

        driver.get("https://demoqa.com/login");

        LoginPage loginPage =
                new LoginPage(driver);

        try {

            DashboardPage dashboard =
                    loginPage
                            .enterUsername(username)
                            .enterPassword(password)
                            .clickLogin();

            boolean actual =
                    dashboard
                            .getLoggedInUsername()
                            .equals(username);

            Assert.assertEquals(actual, expected);

        } catch (Exception e) {

            if (expected) {
                Assert.fail("Expected login success but failed.");
            }
        }
    }
}
