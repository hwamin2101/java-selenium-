package session3.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import session3.base.BasePage;

public class DashboardPage extends BasePage {

    private By profileHeader = By.id("userName-label");
    private By usernameLabel = By.id("userName-value");


    public DashboardPage(WebDriver driver) {
        super(driver);
    }

    public String getHeaderText() {
        return getText(profileHeader);
    }

    public String getLoggedInUsername() {
        return getText(usernameLabel);
    }
}