package session2.exercise4_2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class IFramePage {

    WebDriver driver;
    WebDriverWait wait;

    private By editorBody = By.id("tinymce");

    public IFramePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void switchToIframeByIndex(int index) {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(index));
    }

    public void switchToIframeById() {
        wait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt("mce_0_ifr"));
    }

    public void clearAndType(String text) {

        JavascriptExecutor js = (JavascriptExecutor) driver;

        js.executeScript(
                "document.getElementById('tinymce').innerHTML = arguments[0];",
                text
        );
    }

    public String getEditorText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(editorBody)).getText();
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }

    public void switchToMainContent() {
        driver.switchTo().defaultContent();
    }
}