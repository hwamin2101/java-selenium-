package session1.exercise1_2.tests;

import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_2.locators.ElementLocators;
import session1.utils.ConfigReader;

public class LocatorTest extends BaseTest {

    @Test
    public void verifyLocatorStrategies(){
        driver.get(ConfigReader.get("ex2.url"));

        driver.findElement(ElementLocators.TEXT_BOX_MENU).click();

        driver.findElement(ElementLocators.FULL_NAME).sendKeys("Test");
        driver.findElement(ElementLocators.EMAIL).sendKeys("test123@test.com");
        driver.findElement(ElementLocators.CURRENT_ADDRESS).sendKeys("HN");

        driver.findElement(ElementLocators.CHECK_BOX_MENU).click();
        driver.findElement(ElementLocators.WEB_TABLE_MENU).click();
    }

}
