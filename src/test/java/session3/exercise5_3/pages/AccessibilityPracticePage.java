package session3.exercise5_3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import session3.config.ConfigReader;

import java.time.Duration;
import java.util.List;

public class AccessibilityPracticePage {

    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.id("login");
    private final By usernameInput = By.id("userName");
    private final By passwordInput = By.id("password");
    private final By usernameLabelById = By.id("userName-label");
    private final By usernameLabelByFor = By.cssSelector("label[for='userName']");
    private final By passwordLabelById = By.id("password-label");
    private final By passwordLabelByFor = By.cssSelector("label[for='password']");
    private final By formInteractiveElements = By.cssSelector("#userForm input, #userForm button, #userForm a");
    private final By ariaLabeledElements = By.cssSelector("#userForm [aria-label], [aria-label]");
    private final By roleButtonElements = By.cssSelector("#userForm [role='button'], [role='button']");

    public AccessibilityPracticePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    public void open() {
        driver.get(ConfigReader.get("baseUrl"));
        wait.until(ExpectedConditions.presenceOfElementLocated(usernameInput));
    }

    public WebElement getLoginButton() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton));
    }

    public WebElement getUsernameInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
    }

    public WebElement getPasswordInput() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordInput));
    }

    public WebElement getUsernameLabel() {
        return findFirstExisting(usernameLabelById, usernameLabelByFor);
    }

    public WebElement getPasswordLabel() {
        return findFirstExisting(passwordLabelById, passwordLabelByFor);
    }

    public List<WebElement> getFormInteractiveElements() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        return driver.findElements(formInteractiveElements);
    }


    public List<WebElement> getAriaLabeledElements() {
        wait.until(ExpectedConditions.presenceOfElementLocated(loginButton));
        return driver.findElements(ariaLabeledElements);
    }

    public List<WebElement> getRoleButtonElements() {
        return driver.findElements(roleButtonElements);
    }

    public void focusUsernameInput() {
        getUsernameInput().click();
    }

    public String pressTabAndGetFocusedElementId() {
        new Actions(driver).sendKeys(Keys.TAB).perform();
        return driver.switchTo().activeElement().getAttribute("id");
    }

    public boolean hasAssociatedLabel(String id) {
        if (id == null || id.trim().isEmpty()) {
            return false;
        }
        return !driver.findElements(By.cssSelector("label[for='" + id + "']")).isEmpty();
    }

    private WebElement findFirstExisting(By primary, By fallback) {
        try {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(primary));
        } catch (NoSuchElementException | org.openqa.selenium.TimeoutException e) {
            return wait.until(ExpectedConditions.visibilityOfElementLocated(fallback));
        }
    }
}
