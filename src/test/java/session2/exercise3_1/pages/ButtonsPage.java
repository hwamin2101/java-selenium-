package session2.exercise3_1.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import session2.base.BasePage;

public class ButtonsPage extends BasePage {

    // Buttons
    @FindBy(id = "doubleClickBtn")
    private WebElement doubleClickBtn;

    @FindBy(id = "rightClickBtn")
    private WebElement rightClickBtn;

    // dynamic button
    @FindBy(xpath = "//button[text()='Click Me']")
    private WebElement dynamicClickBtn;

    // Messages
    private final By doubleClickMsg = By.id("doubleClickMessage");
    private final By rightClickMsg = By.id("rightClickMessage");
    private final By dynamicClickMsg = By.id("dynamicClickMessage");
    private final By draggable = By.id("draggable");
    private final By droppable = By.id("droppable");
    private final By dropText = By.xpath("//div[@id='droppable']/p");

    public ButtonsPage(WebDriver driver) {
        super(driver);
    }


    private void scrollTo(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }


    public void doubleClickButton() {
        scrollTo(doubleClickBtn);
        wait.until(ExpectedConditions.elementToBeClickable(doubleClickBtn));

        new Actions(driver)
                .moveToElement(doubleClickBtn)
                .doubleClick()
                .perform();
    }

    public void rightClickButton() {
        scrollTo(rightClickBtn);
        wait.until(ExpectedConditions.elementToBeClickable(rightClickBtn));

        new Actions(driver)
                .contextClick(rightClickBtn)
                .perform();
    }

    public void dragAndDrop(){
        WebElement drag = wait.until(ExpectedConditions.visibilityOfElementLocated(draggable));
        WebElement drop = wait.until(ExpectedConditions.visibilityOfElementLocated(draggable));
        scrollTo(drop);
        new Actions(driver)
                .dragAndDrop(drag,drop)
                .perform();
    }


    public void dynamicClickButton() {
        scrollTo(dynamicClickBtn);
        wait.until(ExpectedConditions.elementToBeClickable(dynamicClickBtn));

        new Actions(driver)
                .moveToElement(dynamicClickBtn)
                .click()
                .perform();
    }

    public boolean isDropped(){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(dropText)).getText().contains("Drop here");
    }


    public String getDoubleClickMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(doubleClickMsg)
        ).getText();
    }

    public String getRightClickMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(rightClickMsg)
        ).getText();
    }

    public String getDynamicClickMessage() {
        return wait.until(
                ExpectedConditions.visibilityOfElementLocated(dynamicClickMsg)
        ).getText();
    }


    public void advancedActionChain() {
        new Actions(driver)
                .keyDown(Keys.CONTROL)
                .sendKeys("a")
                .keyUp(Keys.CONTROL)
                .moveToElement(doubleClickBtn)
                .pause(300)
                .doubleClick()
                .perform();
    }
}
