package session1.exercise1_1.tests;

import org.testng.annotations.Test;
import session1.base.BaseTest;
import session1.utils.ConfigReader;

public class FirstTest extends BaseTest {

    @Test
    public void openGoogle() {
        driver.get(ConfigReader.get("base.url"));
    }
}
