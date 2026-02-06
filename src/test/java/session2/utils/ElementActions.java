package session2.utils;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class ElementActions {

    private final WebDriver driver;
    private final WebDriverWait wait;

    public ElementActions(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void safeClick(By locator) {
        try {
            WebElement element =
                    wait.until(ExpectedConditions.elementToBeClickable(locator));
            element.click();
        } catch (ElementClickInterceptedException e) {
            WebElement element = driver.findElement(locator);
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].scrollIntoView(true);", element);
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    public void type(By locator, String value) {
        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        element.clear();
        element.sendKeys(value);
    }
}
