package session1.exercise1_2.tests;

import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_2.locators.ElementLocators;
import session1.utils.ConfigReader;

public class LocatorTest extends BaseTest {

    @Test
    public void verifyLocatorStrategies() {
        driver.get(ConfigReader.get("ex2.url"));

        actions.safeClick(ElementLocators.menuByText("Text Box"));

        actions.type(
                ElementLocators.inputById("userName"),
                ConfigReader.get("fullName")
        );

        actions.type(
                ElementLocators.inputById("userEmail"),
                ConfigReader.get("email")
        );

        actions.type(
                ElementLocators.CURRENT_ADDRESS,
                ConfigReader.get("currentAddress")
        );

        actions.safeClick(ElementLocators.menuByText("Check Box"));
        actions.safeClick(ElementLocators.menuByText("Web Tables"));
    }
}
