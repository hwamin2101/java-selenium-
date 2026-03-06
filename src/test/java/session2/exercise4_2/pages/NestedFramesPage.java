package session2.exercise4_2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class NestedFramesPage {

    private WebDriver driver;

    public NestedFramesPage(WebDriver driver) {
        this.driver = driver;
    }

    public void switchToTopFrame() {
        driver.switchTo().frame("frame-top");
    }

    public void switchToLeftFrame() {
        driver.switchTo().frame("frame-left");
    }

    public void switchToMiddleFrame() {
        driver.switchTo().frame("frame-middle");
    }

    public String getFrameText() {
        return driver.findElement(By.tagName("body")).getText();
    }

    public void switchToParentFrame() {
        driver.switchTo().parentFrame();
    }

    public void switchToDefault() {
        driver.switchTo().defaultContent();
    }
}