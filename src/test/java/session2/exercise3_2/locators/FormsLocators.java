package session2.exercise3_2.locators;

import org.openqa.selenium.By;

public class FormsLocators {

    public static By gender(String value) {
        return By.xpath("//label[text()='" + value + "']");
    }

    public static By hobby(String value) {
        return By.xpath("//label[text()='" + value + "']");
    }

    public static By dropdownOption(String value) {
        return By.xpath("//div[text()='" + value + "']");
    }

    public static By subjectOption(String value) {
        return By.xpath(
                "//div[contains(@class,'subjects-auto-complete__option') and text()='"
                        + value + "']");
    }

    public static By calendarDay(String day) {
        return By.xpath(
                "//div[contains(@class,'react-datepicker__day') and text()='"
                        + day + "']");
    }
}

