package session1.exercise1_3.tests;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_3.locators.LoginLocators;
import session1.utils.ConfigReader;

import java.time.Duration;

public class LoginTest extends BaseTest {

    @Test
    public void verifyLoginSuccessfully() {

        driver.get(ConfigReader.get("ex3.url"));

        driver.findElement(LoginLocators.USERNAME_INPUT)
                .sendKeys(ConfigReader.get("ex3.username"));

        driver.findElement(LoginLocators.PASSWORD_INPUT)
                .sendKeys(ConfigReader.get("ex3.password"));

        safeClick(LoginLocators.SUBMIT_BUTTON);


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement successMessage = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.SUCCESS_MESSAGE
                )
        );

        Assert.assertEquals(
                successMessage.getText(),
                ConfigReader.get("ex3.success.text")
        );


        String parentWindow = driver.getWindowHandle();
        String successUrl = driver.getCurrentUrl();

        driver.switchTo().newWindow(WindowType.TAB);
        driver.get(successUrl);

        WebElement newTabSuccess = wait.until(
                ExpectedConditions.visibilityOfElementLocated(
                        LoginLocators.SUCCESS_MESSAGE
                )
        );

        Assert.assertEquals(
                newTabSuccess.getText(),
                ConfigReader.get("ex3.success.text")
        );

        driver.switchTo().window(parentWindow);
    }

}
