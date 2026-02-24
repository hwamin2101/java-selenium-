package session2.exercise3_1.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise3_1.pages.ButtonsPage;

public class ButtonsTest extends BaseTest {
    @Test
    public void testButtonActions() {
        driver.get(ConfigReader.get("ex3.button"));
        ButtonsPage buttonsPage = new ButtonsPage(driver);

        // Test 1: Double click
        buttonsPage.doubleClickButton();
        Assert.assertTrue(buttonsPage.getDoubleClickMessage().contains("You have done a double click"));

        // Test 2: Right click
        buttonsPage.rightClickButton();
        Assert.assertTrue(buttonsPage.getRightClickMessage().contains("You have done a right click"));

        // Test 3: Dynamic click
        buttonsPage.dynamicClickButton();
        Assert.assertTrue(buttonsPage.getDynamicClickMessage().contains("You have done a dynamic click"));

        // Advanced test
        buttonsPage.advancedActionChain();

        // Test 4: drag and drop button
        driver.get(ConfigReader.get("ex3.dragAndDrop"));
        ButtonsPage buttonsPage1 = new ButtonsPage(driver);
        buttonsPage1.dragAndDrop();
        Assert.assertTrue(buttonsPage1.isDropped());
    }
}
