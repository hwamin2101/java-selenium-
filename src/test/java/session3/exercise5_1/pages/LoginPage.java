package session3.exercise5_1.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import session3.base.BasePage;

public class LoginPage extends AuthPage {

    private By username = By.id("userName");
    private By password = By.id("password");
    private By loginBtn = By.id("login");

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public LoginPage enterUsername(String user) {
        sendKeys(username, user);
        return this;
    }

    public LoginPage enterPassword(String pass) {
        sendKeys(password, pass);
        return this;
    }

    public DashboardPage clickLogin() {
        click(loginBtn);
        return new DashboardPage(driver);
    }
}