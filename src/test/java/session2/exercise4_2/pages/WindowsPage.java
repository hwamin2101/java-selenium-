package session2.exercise4_2.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class WindowsPage {

    private WebDriver driver;

    private By clickHereLink = By.linkText("Click Here");
    private By newWindowText = By.tagName("h3");

    public WindowsPage(WebDriver driver) {
        this.driver = driver;
    }

    public String getMainWindowHandle() {
        return driver.getWindowHandle();
    }

    public void clickOpenNewWindow() {
        driver.findElement(clickHereLink).click();
    }

    public String getNewWindowText() {
        return driver.findElement(newWindowText).getText();
    }

    public void switchToNewWindow(String mainWindow) {

        for (String window : driver.getWindowHandles()) {

            if (!window.equals(mainWindow)) {

                driver.switchTo().window(window);
                break;
            }
        }
    }

    public void switchToMainWindow(String mainWindow) {
        driver.switchTo().window(mainWindow);
    }
}