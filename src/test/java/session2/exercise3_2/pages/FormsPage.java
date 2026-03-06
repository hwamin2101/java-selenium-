package session2.exercise3_2.pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import session2.base.BasePage;
import session2.exercise3_2.locators.FormsLocators;

public class FormsPage extends BasePage {

    public FormsPage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "firstName")
    private WebElement firstName;

    @FindBy(id = "lastName")
    private WebElement lastName;

    @FindBy(id = "userEmail")
    private WebElement email;

    @FindBy(id = "userNumber")
    private WebElement mobile;

    @FindBy(id = "currentAddress")
    private WebElement address;

    @FindBy(id = "subjectsInput")
    private WebElement subjectInput;

    @FindBy(id = "dateOfBirthInput")
    private WebElement dateInput;

    @FindBy(id = "state")
    private WebElement stateDropdown;

    @FindBy(id = "city")
    private WebElement cityDropdown;

    @FindBy(id = "submit")
    private WebElement submitBtn;

    private final By successModal =
            By.id("example-modal-sizes-title-lg");


    public void fillBasicInfo(String f, String l, String mail, String addr, String mobileNum) {
        firstName.sendKeys(f);
        lastName.sendKeys(l);
        email.sendKeys(mail);
        mobile.sendKeys(mobileNum);
        address.sendKeys(addr);
    }

    public void selectGender(String gender) {
        click(driver.findElement(FormsLocators.gender(gender)));
    }

    public void selectHobby(String hobby) {
        click(driver.findElement(FormsLocators.hobby(hobby)));
    }

    public void addSubject(String subject) {
        subjectInput.sendKeys(subject);
        click(driver.findElement(FormsLocators.subjectOption(subject)));
    }

    public void selectDate(String month, String year, String day) {

        dateInput.click();

        driver.findElement(By.className("react-datepicker__month-select"))
                .sendKeys(month);

        driver.findElement(By.className("react-datepicker__year-select"))
                .sendKeys(year);

        click(driver.findElement(FormsLocators.calendarDay(day)));
    }

    public void selectStateCity(String state, String city) {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", stateDropdown);

        click(stateDropdown);
        click(driver.findElement(FormsLocators.dropdownOption(state)));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", cityDropdown);

        click(cityDropdown);
        click(driver.findElement(FormsLocators.dropdownOption(city)));
    }



    public void submit() {

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", submitBtn);

        submitBtn.click();
    }

    public boolean isSubmitted() {
        return wait.until(
                        ExpectedConditions.visibilityOfElementLocated(successModal))
                .isDisplayed();
    }
}
