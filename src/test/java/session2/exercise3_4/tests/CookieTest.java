package session2.exercise3_4.tests;

import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import session2.base.BaseTest;
import session2.exercise3_4.utils.CookieUtils;
import session2.utils.ConfigReader;


public class CookieTest extends BaseTest {

        @Test
        public void testCookieOperations() {

                driver.get(ConfigReader.get("cookie.url"));

                CookieUtils.addCookie(driver, "testCookie", "123");

                CookieUtils.getCookie(driver, "testCookie");

                CookieUtils.getAllCookies(driver);

                CookieUtils.deleteCookie(driver, "testCookie");

                CookieUtils.deleteAllCookies(driver);
        }
}