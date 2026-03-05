package session3.exercise5_3.tests;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import session3.base.BaseTest;
import session3.config.ConfigReader;
import session3.exercise5_1.pages.DashboardPage;
import session3.exercise5_1.pages.LoginPage;
import session3.exercise5_3.pages.AccessibilityPracticePage;
import session3.exercise5_3.utils.ColorContrastUtil;

import java.util.List;

public class AccessibilityAdvancedTest extends BaseTest {

    private static final double MIN_CONTRAST_RATIO_NORMAL_TEXT = 4.5;
    private AccessibilityPracticePage page;

    @BeforeMethod
    public void openAccessibilityPracticePage() {
        page = new AccessibilityPracticePage(driver);
        page.open();
    }

    @Test
    public void verifyAriaLabelAndRoleAttributes() {
        List<WebElement> ariaElements = page.getAriaLabeledElements();
        String ariaLabel;
        if (ariaElements.isEmpty()) {
            ariaLabel = page.getUsernameInput().getAttribute("aria-label");
        } else {
            ariaLabel = ariaElements.get(0).getAttribute("aria-label");
        }

        String ariaLabelledBy = page.getUsernameInput().getAttribute("aria-labelledby");
        String placeholder = page.getUsernameInput().getAttribute("placeholder");
        boolean hasAriaName = hasNonEmptyValue(ariaLabel)
                || hasNonEmptyValue(ariaLabelledBy)
                || hasNonEmptyValue(placeholder);
        Assert.assertTrue(hasAriaName,
                "Element should provide aria-label, aria-labelledby or placeholder.");

        List<WebElement> roleButtons = page.getRoleButtonElements();
        WebElement roleElement = roleButtons.isEmpty()
                ? page.getLoginButton()
                : roleButtons.get(0);

        String role = roleElement.getAttribute("role");
        if (!hasNonEmptyValue(role) && "button".equalsIgnoreCase(roleElement.getTagName())) {
            role = "button";
        }

        Assert.assertEquals(role, "button",
                "Role is incorrect for button-like element.");
    }

    @Test
    public void verifyKeyboardNavigationTabOrder() {
        page.focusUsernameInput();

        Assert.assertEquals(page.pressTabAndGetFocusedElementId(), "password",
                "TAB from username should focus password.");
        Assert.assertEquals(page.pressTabAndGetFocusedElementId(), "login",
                "TAB from password should focus login button.");
    }

    @Test
    public void verifyColorContrastForCriticalElements() {
        assertContrastPassesAa(page.getLoginButton(), MIN_CONTRAST_RATIO_NORMAL_TEXT);
        assertContrastPassesAa(page.getUsernameLabel(), MIN_CONTRAST_RATIO_NORMAL_TEXT);
    }

    @Test
    public void verifyScreenReaderCompatibility() {
        WebElement username = page.getUsernameInput();
        WebElement password = page.getPasswordInput();
        WebElement usernameLabel = page.getUsernameLabel();
        WebElement passwordLabel = page.getPasswordLabel();

        boolean validUsernameBinding = "userName".equals(usernameLabel.getAttribute("for"))
                || "userName-label".equals(usernameLabel.getAttribute("id"));
        boolean validPasswordBinding = "password".equals(passwordLabel.getAttribute("for"))
                || "password-label".equals(passwordLabel.getAttribute("id"));

        Assert.assertTrue(validUsernameBinding,
                "Username label must be associated with username input.");
        Assert.assertTrue(validPasswordBinding,
                "Password label must be associated with password input.");

        Assert.assertTrue(hasNonEmptyValue(username.getAttribute("id")));
        Assert.assertTrue(hasNonEmptyValue(password.getAttribute("id")));
        Assert.assertTrue(hasNonEmptyValue(username.getAttribute("placeholder")));
        Assert.assertTrue(hasNonEmptyValue(password.getAttribute("placeholder")));
    }

    @Test
    public void verifyComprehensiveAccessibilitySuiteAdvanced() {
        List<WebElement> interactiveElements = page.getFormInteractiveElements();

        Assert.assertFalse(interactiveElements.isEmpty(),
                "Form interactive elements should exist.");

        for (WebElement element : interactiveElements) {
            Assert.assertTrue(element.isDisplayed(),
                    "Interactive element must be visible: " + element.getTagName());

            String id = element.getAttribute("id");
            String ariaLabel = element.getAttribute("aria-label");
            String title = element.getAttribute("title");
            String text = element.getText();
            String placeholder = element.getAttribute("placeholder");

            boolean hasAccessibleName = hasNonEmptyValue(ariaLabel)
                    || hasNonEmptyValue(title)
                    || hasNonEmptyValue(text)
                    || hasNonEmptyValue(placeholder)
                    || page.hasAssociatedLabel(id);

            Assert.assertTrue(hasAccessibleName,
                    "Element does not have accessible name. id=" + id);
        }


        assertContrastPassesAa(page.getLoginButton(), MIN_CONTRAST_RATIO_NORMAL_TEXT);

        DashboardPage dashboard = new LoginPage(driver)
                .enterUsername(ConfigReader.get("username"))
                .enterPassword(ConfigReader.get("password"))
                .clickLogin();

        Assert.assertEquals(dashboard.getLoggedInUsername(),
                ConfigReader.get("username"),
                "Login should be successful with existing test data.");
    }

    private boolean hasNonEmptyValue(String value) {
        return value != null && !value.trim().isEmpty();
    }

    private void assertContrastPassesAa(WebElement element, double minRatio) {
        String foreground = element.getCssValue("color");
        String background = element.getCssValue("background-color");

        if (ColorContrastUtil.isTransparent(background)) {
            background = "rgb(255, 255, 255)";
        }

        double ratio = ColorContrastUtil.calculateContrastRatio(foreground, background);
        Assert.assertTrue(ratio >= minRatio,
                "Contrast ratio too low: " + ratio + " for element id="
                        + element.getAttribute("id"));
    }
}
