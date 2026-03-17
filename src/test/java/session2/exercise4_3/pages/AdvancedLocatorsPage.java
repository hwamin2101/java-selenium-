package session2.exercise4_3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AdvancedLocatorsPage {

    private WebDriver driver;

    // XPath text()
    private By submitButtonText =
            By.xpath("//button[text()='Submit']");

    // XPath contains()
    private By userIdContains =
            By.xpath("//input[contains(@id,'userName')]");

    // XPath normalize-space()
    private By normalizeSpaceExample =
            By.xpath("//label[normalize-space()='Full Name']");

    // XPath starts-with()
    private By errorStartsWith =
            By.xpath("//input[starts-with(@id,'user')]");

    // XPath position()
    private By thirdButton =
            By.xpath("(//button)[3]");

    // CSS attribute selector
    private By textInputCSS =
            By.cssSelector("input[type='text']");

    // CSS child combinator
    private By formChildInput =
            By.cssSelector("form input");

    // CSS class selector
    private By errorClassCSS =
            By.cssSelector(".form-control");

    // Optimized XPath
    private By optimizedXPath =
            By.xpath("//input[contains(@placeholder,'First Name')]");

    // Optimized CSS
    private By optimizedCSS =
            By.cssSelector("input[placeholder*='First Name']");

    public AdvancedLocatorsPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getSubmitButton() {
        return driver.findElement(submitButtonText);
    }

    public WebElement getUserIdField() {
        return driver.findElement(userIdContains);
    }

    public WebElement getNormalizeSpaceElement() {
        return driver.findElement(normalizeSpaceExample);
    }

    public WebElement getErrorElement() {
        return driver.findElement(errorStartsWith);
    }

    public WebElement getThirdButton() {
        return driver.findElement(thirdButton);
    }

    public WebElement getTextInputCSS() {
        return driver.findElement(textInputCSS);
    }

    public WebElement getFormChildInput() {
        return driver.findElement(formChildInput);
    }

    public WebElement getErrorClassCSS() {
        return driver.findElement(errorClassCSS);
    }

    public WebElement getOptimizedXPathElement() {
        return driver.findElement(optimizedXPath);
    }

    public WebElement getOptimizedCSSElement() {
        return driver.findElement(optimizedCSS);
    }

}