package session2.exercise4_1.tests;

import org.openqa.selenium.Alert;
import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise4_1.pages.AlertsPage;

public class AlertsTest extends BaseTest {

    @Test(priority = 1)
    public void testSimpleAlertAccept() {

        driver.get(ConfigReader.get("alert.url"));
        AlertsPage page = new AlertsPage(driver);

        page.clickSimpleAlert();
        Alert alert = page.waitForAlert();
        alert.accept();

        Assert.assertEquals(
                page.getResultText(),
                "You successfully clicked an alert"
        );
    }

    @Test(priority = 2)
    public void testConfirmAlertDismiss() {

        driver.get(ConfigReader.get("alert.url"));
        AlertsPage page = new AlertsPage(driver);

        page.clickConfirmAlert();
        Alert alert = page.waitForAlert();
        alert.dismiss();

        Assert.assertEquals(
                page.getResultText(),
                "You clicked: Cancel"
        );
    }

    @Test(priority = 3)
    public void testPromptAlertSendKeysAccept() {

        driver.get(ConfigReader.get("alert.url"));
        AlertsPage page = new AlertsPage(driver);

        page.clickPromptAlert();
        Alert alert = page.waitForAlert();

        String input = "Hwamin Test";
        alert.sendKeys(input);
        alert.accept();

        Assert.assertEquals(
                page.getResultText(),
                "You entered: " + input
        );
    }

    @Test(priority = 4)
    public void testMultipleAlertsSequence() {

        driver.get(ConfigReader.get("alert.url"));
        AlertsPage page = new AlertsPage(driver);

        page.clickSimpleAlert();
        page.waitForAlert().accept();

        page.clickConfirmAlert();
        page.waitForAlert().dismiss();

        page.clickPromptAlert();
        Alert alert = page.waitForAlert();
        String text = "Advanced Alert Test";
        alert.sendKeys(text);
        alert.accept();

        Assert.assertEquals(
                page.getResultText(),
                "You entered: " + text
        );
    }

    @Test(priority = 5)
    public void testUnexpectedAlertHandling() {

        driver.get(ConfigReader.get("alert.url"));
        AlertsPage page = new AlertsPage(driver);

        if (page.isAlertPresent(3)) {
            driver.switchTo().alert().accept();
        }

        Assert.assertTrue(true);
    }
}