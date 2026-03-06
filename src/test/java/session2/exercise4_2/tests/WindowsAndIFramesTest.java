package session2.exercise4_2.tests;

import org.openqa.selenium.Dimension;
import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise4_2.pages.IFramePage;
import session2.exercise4_2.pages.NestedFramesPage;
import session2.exercise4_2.pages.WindowsPage;

import java.util.Set;

public class WindowsAndIFramesTest extends BaseTest {

    @Test(priority = 1)
    public void testMultipleWindowsHandling() {

        driver.get(ConfigReader.get("window.url"));

        WindowsPage page = new WindowsPage(driver);

        String mainWindow = page.getMainWindowHandle();

        page.clickOpenNewWindow();

        Set<String> allWindows = driver.getWindowHandles();

        String newWindow = null;

        for (String window : allWindows) {
            if (!window.equals(mainWindow)) {
                newWindow = window;
            }
        }

        driver.switchTo().window(newWindow);

        Assert.assertEquals(
                page.getNewWindowText(),
                "New Window"
        );

        driver.close();

        driver.switchTo().window(mainWindow);

        Assert.assertEquals(
                driver.getTitle(),
                "The Internet"
        );
    }

    @Test(priority = 2)
    public void testWindowSizeManagement() {

        driver.manage().window().setSize(new Dimension(1024, 768));

        Dimension size = driver.manage().window().getSize();

        Assert.assertEquals(size.getWidth(), 1024);
        Assert.assertEquals(size.getHeight(), 768);
    }

    @Test(priority = 3)
    public void testIframeByIndex() {

        driver.get(ConfigReader.get("iframe.url"));

        IFramePage iframePage = new IFramePage(driver);

        iframePage.switchToIframeByIndex(0);

        iframePage.clearAndType("Test iframe index");

        Assert.assertEquals(
                iframePage.getEditorText(),
                "Test iframe index"
        );

        iframePage.switchToDefault();
    }

    @Test(priority = 4)
    public void testIframeById() {

        driver.get(ConfigReader.get("iframe.url"));

        IFramePage iframePage = new IFramePage(driver);

        iframePage.switchToIframeById();

        iframePage.clearAndType("Test iframe id");

        Assert.assertEquals(
                iframePage.getEditorText(),
                "Test iframe id"
        );

        iframePage.switchToMainContent();
    }

    @Test(priority = 5)
    public void testNestedFramesHandling() {

        driver.get(ConfigReader.get("nestedframe.url"));

        NestedFramesPage nested = new NestedFramesPage(driver);

        nested.switchToTopFrame();

        nested.switchToLeftFrame();

        Assert.assertEquals(
                nested.getFrameText(),
                "LEFT"
        );

        nested.switchToParentFrame();

        nested.switchToMiddleFrame();

        Assert.assertEquals(
                nested.getFrameText(),
                "MIDDLE"
        );

        nested.switchToDefault();
    }

    @Test(priority = 6)
    public void testDynamicWindowCreation() {

        driver.get(ConfigReader.get("dynamic.window.url"));

        WindowsPage page = new WindowsPage(driver);

        String mainWindow = page.getMainWindowHandle();

        page.clickOpenNewWindow();

        Set<String> windows = driver.getWindowHandles();

        Assert.assertTrue(windows.size() > 1);

        for (String window : windows) {

            if (!window.equals(mainWindow)) {

                driver.switchTo().window(window);

                Assert.assertEquals(
                        page.getNewWindowText(),
                        "New Window"
                );

                driver.close();
            }
        }

        driver.switchTo().window(mainWindow);

        Assert.assertEquals(
                driver.getTitle(),
                "The Internet"
        );
    }
}