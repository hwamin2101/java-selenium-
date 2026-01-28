package session1.exercise1_3.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import session1.base.BasePage;
import session1.utils.ConfigReader;

public class SuccessPage extends BasePage {

    @FindBy(tagName = "h1")
    private WebElement successMessage;

    public SuccessPage(WebDriver driver) {
        super(driver);
    }

    public String getSuccessText() {
        WebElement visibleMessage = wait.until(
                ExpectedConditions.visibilityOf(successMessage)
        );
        return visibleMessage.getText();
    }



    public boolean isLoggedIn() {
        return getSuccessText().equals(ConfigReader.get("ex3.success.text"));
    }
}

