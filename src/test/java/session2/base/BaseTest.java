package session2.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import session1.utils.ConfigReader;
import session1.utils.ElementActions;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;


public class BaseTest {

    protected WebDriver driver;
    protected ElementActions actions;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
//        String browser = ConfigReader.get("browser");
        if (browser == null || browser.isEmpty()) {
            browser = "chrome";
        }

        ChromeOptions options = new ChromeOptions();
        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();

            options = new ChromeOptions();

            if ("true".equalsIgnoreCase(ConfigReader.get("headless"))) {
                options.addArguments("--headless=new");
            }

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            String downloadPath = Paths
                    .get(ConfigReader.get("download.dir"))
                    .toAbsolutePath()
                    .toString();
            Map<String, Object> prefs = new HashMap<>();
            prefs.put("download.default_directory", downloadPath);
            prefs.put("download.prompt_for_download", false);
            prefs.put("profile.default_content_settings.popups", 0);
            options.setExperimentalOption("prefs", prefs);

            driver = new ChromeDriver(options);
            actions = new ElementActions(driver);
        }
        if (driver == null) {
            throw new RuntimeException("WebDriver is not initialized" + browser);
        }

        driver.manage().window().maximize();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    protected void safeClick(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement element = wait.until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView({block:'center'});", element);

        try {
            wait.until(ExpectedConditions.elementToBeClickable(locator)).click();
        } catch (ElementClickInterceptedException e) {
            ((JavascriptExecutor) driver)
                    .executeScript("arguments[0].click();", element);
        }
    }
    protected void reopenDriver() {

        if (driver != null) {
            driver.quit();
        }

        ChromeOptions options = new ChromeOptions();

        if ("true".equalsIgnoreCase(ConfigReader.get("headless"))) {
            options.addArguments("--headless=new");
        }

        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        String downloadPath = Paths
                .get(ConfigReader.get("download.dir"))
                .toAbsolutePath()
                .toString();

        Map<String, Object> prefs = new HashMap<>();
        prefs.put("download.default_directory", downloadPath);
        prefs.put("download.prompt_for_download", false);
        prefs.put("profile.default_content_settings.popups", 0);

        options.setExperimentalOption("prefs", prefs);

        driver = new ChromeDriver(options);
        actions = new ElementActions(driver);

        driver.manage().window().maximize();
    }

}

