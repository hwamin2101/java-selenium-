package session2.exercise3_4.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import session1.utils.ConfigReader;

public class LoginPage {

    WebDriver driver;

    By username = By.id("userName");
    By password = By.id("password");
    By loginBtn = By.id("login");

    public LoginPage(WebDriver driver) {

        this.driver = driver;

    }

    public void open() {

        driver.get(ConfigReader.get("cookie.url"));

    }

    public void login() {

        driver.findElement(username)
                .sendKeys(ConfigReader.get("cookie.username"));

        driver.findElement(password)
                .sendKeys(ConfigReader.get("cookie.password"));

        driver.findElement(loginBtn)
                .click();

    }

}