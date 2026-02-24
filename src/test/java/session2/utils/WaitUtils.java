package session2.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    public static WebDriverWait getWait(WebDriver driver) {
        int timeout = Integer.parseInt(ConfigReader.get("explicit.wait"));
        return new WebDriverWait(driver, Duration.ofSeconds(timeout));
    }

    public static ExpectedCondition<Boolean> elementInvisible(By locator) {
        return driver -> driver.findElements(locator).isEmpty()
                || !driver.findElement(locator).isDisplayed();
    }
}
