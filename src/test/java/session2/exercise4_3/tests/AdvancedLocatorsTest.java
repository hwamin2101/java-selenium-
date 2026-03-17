package session2.exercise4_3.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise4_3.pages.AdvancedLocatorsPage;

public class AdvancedLocatorsTest extends BaseTest {

    @Test(priority = 1)
    public void testXPathTextLocator() {

        driver.get(ConfigReader.get("demoqa.textbox.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getSubmitButton().isDisplayed());
    }

    @Test(priority = 2)
    public void testXPathContainsLocator() {

        driver.get(ConfigReader.get("demoqa.textbox.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getUserIdField().isDisplayed());
    }

    @Test(priority = 3)
    public void testXPathNormalizeSpaceLocator() {

        driver.get(ConfigReader.get("demoqa.textbox.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getNormalizeSpaceElement().isDisplayed());
    }

    @Test(priority = 4)
    public void testXPathStartsWithLocator() {

        driver.get(ConfigReader.get("demoqa.textbox.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getErrorElement().isDisplayed());
    }

    @Test(priority = 5)
    public void testXPathPositionLocator() {

        driver.get(ConfigReader.get("demoqa.buttons.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getThirdButton().isDisplayed());
    }

    @Test(priority = 6)
    public void testCSSAttributeSelector() {

        driver.get(ConfigReader.get("demoqa.textbox.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getTextInputCSS().isDisplayed());
    }

    @Test(priority = 7)
    public void testCSSChildCombinator() {

        driver.get(ConfigReader.get("demoqa.forms.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getFormChildInput().isDisplayed());
    }

    @Test(priority = 8)
    public void testCSSClassSelector() {

        driver.get(ConfigReader.get("demoqa.forms.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getErrorClassCSS().isDisplayed());
    }

    @Test(priority = 9)
    public void testOptimizedXPathLocator() {

        driver.get(ConfigReader.get("demoqa.forms.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getOptimizedXPathElement().isDisplayed());
    }

    @Test(priority = 10)
    public void testOptimizedCSSLocator() {

        driver.get(ConfigReader.get("demoqa.forms.url"));

        AdvancedLocatorsPage page = new AdvancedLocatorsPage(driver);

        Assert.assertTrue(page.getOptimizedCSSElement().isDisplayed());
    }

}