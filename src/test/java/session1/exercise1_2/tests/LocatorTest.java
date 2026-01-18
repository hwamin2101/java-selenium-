package session1.exercise1_2.tests;

import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_2.locators.ElementLocators;
import session1.utils.ConfigReader;

public class LocatorTest extends BaseTest {

    @Test
    public void verifyLocatorStrategies() {
        driver.get(ConfigReader.get("ex2.url"));

        driver.findElement(
                ElementLocators.menuByText("Text Box")
        ).click();

        driver.findElement(
                ElementLocators.inputById("userName")
        ).sendKeys(ConfigReader.get("fullName"));

        driver.findElement(
                ElementLocators.inputById("userEmail")
        ).sendKeys(ConfigReader.get("email"));

        driver.findElement(
                ElementLocators.CURRENT_ADDRESS
        ).sendKeys(ConfigReader.get("currentAddress"));

        driver.findElement(
                ElementLocators.menuByText("Check Box")
        ).click();

        driver.findElement(
                ElementLocators.menuByText("Web Tables")
        ).click();
    }
}
