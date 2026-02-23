package session2.base;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {
    protected WebDriver driver;
    protected WebDriverWait wait;

    public BasePage(WebDriver driver){
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        PageFactory.initElements(driver, this);
    }

    public void click(WebElement element){
        wait.until(ExpectedConditions.elementToBeClickable(element)).click();
    }
    public void sendKeys(WebElement element, String text){
        element.clear();
        element.sendKeys(text);
    }
    public String getText(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).getText();

    }
    public boolean isDisplayed(WebElement element){
        return wait.until(ExpectedConditions.visibilityOf(element)).isDisplayed();
    }
 public void selectValue(WebElement dropdown, String value) {
            wait.until(ExpectedConditions.elementToBeClickable(dropdown)).click();

            By optionLocator = By.xpath("//div[contains(@class, 'option') and contains(., '" + value + "')]");
            WebElement option = wait.until(ExpectedConditions.elementToBeClickable(optionLocator));

            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", option);
            option.click();
        }

}
