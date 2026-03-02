package session3.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import session3.base.BasePage;

public class AuthPage extends BasePage {

    protected By logo = By.id("logo");

    public AuthPage(WebDriver driver) {
        super(driver);
    }

    public boolean isLogoDisplayed() {
        return driver.findElement(logo).isDisplayed();
    }
}