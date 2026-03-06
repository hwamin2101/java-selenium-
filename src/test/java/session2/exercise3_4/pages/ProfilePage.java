package session2.exercise3_4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import session2.base.BasePage;


public class ProfilePage {

    WebDriver driver;

    By usernameLabel = By.id("userName-value");

    public ProfilePage(WebDriver driver) {

        this.driver = driver;

    }

    public boolean isLoggedIn() {

        try {

            return driver.findElement(usernameLabel)
                    .isDisplayed();

        }

        catch (Exception e) {

            return false;

        }

    }

}