package session2.exercise3_4.tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import session2.base.BaseTest;
import session2.exercise3_4.pages.LoginPage;
import session2.exercise3_4.pages.ProfilePage;
import session2.exercise3_4.utils.CookieUtils;
import session2.utils.ConfigReader;


public class SessionPersistenceTest extends BaseTest {

        @Test
        public void testSessionPersistence() throws InterruptedException {

                String cookieFile = ConfigReader.get("cookie.file");

                LoginPage loginPage = new LoginPage(driver);
                ProfilePage profilePage = new ProfilePage(driver);

                // STEP 1: Login
                loginPage.open();
                loginPage.login();

                Thread.sleep(3000);

                Assert.assertTrue(profilePage.isLoggedIn());

                System.out.println("Login success");

                // STEP 2: Print auth cookies
                CookieUtils.printAuthCookies(driver);

                // STEP 3: Save cookies
                CookieUtils.saveCookies(driver, cookieFile);

                // STEP 4: Simulate new session
                reopenDriver();

                driver.get("https://demoqa.com");

                // STEP 5: Load cookies
                CookieUtils.loadCookies(driver, cookieFile);

                driver.navigate().refresh();

                driver.get("https://demoqa.com/profile");

                Thread.sleep(3000);

                profilePage = new ProfilePage(driver);

                Assert.assertTrue(profilePage.isLoggedIn());

                System.out.println("Session restored SUCCESS");
        }
}
