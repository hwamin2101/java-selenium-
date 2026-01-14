package session1.exercise1_2.locators;

import org.openqa.selenium.By;

public class ElementLocators {
    public static final By FULL_NAME = By.id("userName");

    public static final By EMAIL = By.id("userEmail");

    public static final By SUBMIT_BTN = By.cssSelector("#submit");

    public static final By CURRENT_ADDRESS = By.cssSelector("textarea[placeholder='Current Address']");

    public static final By TEXT_BOX_MENU = By.xpath("//span[text()='Text Box']");

    public static final By CHECK_BOX_MENU = By.xpath("//span[@class='text' and text()='Check Box']");

    public static final By WEB_TABLE_MENU = By.xpath("//span[text()='Web Tables']");

    public static final By RADIO_BTN_MENU = By.xpath("//span[contains(text(), 'Radio')]");

    public static final By BTN_MENU = By.xpath("//span[normalize-space()='Buttons']");

    public static final By ELEMENTS_HEADER = By.xpath("//div[conatin(@class, 'main-header')]");
}
