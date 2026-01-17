package session1.exercise1_2.locators;

import org.openqa.selenium.By;

public class ElementLocators {

    private static final String INPUT_BY_ID = "//input[@id='%s']";
    private static final String MENU_BY_TEXT =
            "//span[@class='text' and normalize-space()='%s']";

    public static By inputById(String id) {
        return By.xpath(String.format(INPUT_BY_ID, id));
    }

    public static By menuByText(String text) {
        return By.xpath(String.format(MENU_BY_TEXT, text));
    }

    public static final By FULL_NAME =
            By.xpath("//label[text()='Full Name']/ancestor::div//input");

    public static final By EMAIL =
            By.xpath("//label[text()='Email']/following::input[1]");

    public static final By SUBMIT_BTN = By.cssSelector("#submit");

    public static final By CURRENT_ADDRESS =
            By.cssSelector("textarea[placeholder='Current Address']");

    public static final By ELEMENTS_HEADER =
            By.xpath("//div[contains(@class,'main-header')]");
}
