package session1.base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;
import org.testng.annotations.Parameters;
import session1.utils.ConfigReader;
import session1.utils.ElementActions;

public class BaseTest {

    protected WebDriver driver;
    protected ElementActions actions;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(@Optional("chrome") String browser) {
//        String browser = ConfigReader.get("browser");
        if(browser == null || browser.isEmpty()){
            browser = "chrome";
        }

        if ("chrome".equalsIgnoreCase(browser)) {
            WebDriverManager.chromedriver().setup();

            ChromeOptions options = new ChromeOptions();

            if ("true".equalsIgnoreCase(ConfigReader.get("headless"))) {
                options.addArguments("--headless=new");
            }

            options.addArguments("--no-sandbox");
            options.addArguments("--disable-dev-shm-usage");
            options.addArguments("--window-size=1920,1080");

            driver = new ChromeDriver(options);
            actions = new ElementActions(driver);
        }
        if (driver == null){
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
}
