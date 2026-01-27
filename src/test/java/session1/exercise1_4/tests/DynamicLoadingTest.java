package session1.exercise1_4.tests;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.exercise1_4.locators.DynamicLoadingLocators;
import session1.utils.ConfigReader;
import session1.utils.WaitUtils;

public class DynamicLoadingTest extends BaseTest {

    @Test
    public void verifyHelloWorldAppearsAfterLoading() {

        driver.get(ConfigReader.get("ex4.url"));

        driver.findElement(DynamicLoadingLocators.START_BUTTON).click();

        WaitUtils.getWait(driver)
                .until(WaitUtils.elementInvisible(DynamicLoadingLocators.LOADING_BAR));

        WebElement finishText = WaitUtils.getWait(driver)
                .until(ExpectedConditions.visibilityOfElementLocated(
                        DynamicLoadingLocators.FINISH_TEXT
                ));

        Assert.assertEquals(
                finishText.getText(),
                ConfigReader.get("ex4.success.text"),
                "Hello World text is incorrect"
        );
    }
}
