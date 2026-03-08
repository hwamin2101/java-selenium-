package session3.exercise5_1.tests;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import session3.base.BaseTest;
import session3.config.ConfigReader;

import java.time.Duration;

public class AccessibilityTest extends BaseTest {

    @Test
    public void verifyInputsHaveRequiredAttributes() {
        driver.get(ConfigReader.get("baseUrl"));

        WebElement username = driver.findElement(By.id("userName"));
        WebElement password = driver.findElement(By.id("password"));

        Assert.assertNotNull(username.getAttribute("id"));
        Assert.assertNotNull(password.getAttribute("id"));

        Assert.assertNotNull(username.getAttribute("name"));
        Assert.assertNotNull(password.getAttribute("name"));
    }

    @Test
    public void verifyTabNavigationWorks() {
        driver.get(ConfigReader.get("baseUrl"));

        Actions actions = new Actions(driver);

        actions.sendKeys(Keys.TAB).perform();

        WebElement activeElement = driver.switchTo().activeElement();

        Assert.assertNotNull(activeElement,
                "Tab navigation failed!");
    }

    @Test
    public void verifyLoginUsingEnterKey() {
        driver.get(ConfigReader.get("baseUrl"));

        driver.findElement(By.id("userName"))
                .sendKeys("Hwamin211");

        driver.findElement(By.id("password"))
                .sendKeys("Zhengpeng0706#", Keys.ENTER);

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        WebElement usernameValue = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        By.id("userName-value")));

        Assert.assertTrue(usernameValue.isDisplayed(),
                "Login via ENTER key failed!");
    }
}