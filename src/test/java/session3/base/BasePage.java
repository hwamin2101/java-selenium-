package session3.base;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import java.time.Duration;

public class BasePage {

    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    // ================= CLICK =================

    protected void click(By locator) {

        WebElement element = wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );

        scrollIntoView(element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException e) {

            // fallback JS click
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }

    // ================= SEND KEYS =================

    protected void sendKeys(By locator, String text) {
        WebElement element =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        scrollIntoView(element);
        element.clear();
        element.sendKeys(text);
    }

    // ================= GET TEXT =================

    protected String getText(By locator) {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        ).getText();
    }

    // ================= SELECT DROPDOWN =================

    protected void selectDropdown(By locator, String visibleText) {
        WebElement dropdown =
                wait.until(ExpectedConditions.visibilityOfElementLocated(locator));

        Select select = new Select(dropdown);
        select.selectByVisibleText(visibleText);
    }

    // ================= WAIT HELPERS =================

    protected void waitForUrlContains(String value) {
        wait.until(ExpectedConditions.urlContains(value));
    }

    protected void waitForVisible(By locator) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    // ================= PRIVATE UTIL =================

    private void scrollIntoView(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);
    }
}