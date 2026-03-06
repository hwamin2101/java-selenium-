package session2.exercise3_2.tests;


import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise3_2.pages.FormsPage;

public class FormsTest extends BaseTest {

    @Test
    public void testFormSubmission() {

        driver.get(ConfigReader.get("form.url"));

        FormsPage form = new FormsPage(driver);

        form.fillBasicInfo(
                ConfigReader.get("firstName"),
                ConfigReader.get("lastName"),
                ConfigReader.get("email.form"),
                ConfigReader.get("address"),
                ConfigReader.get("mobile.form")
        );

        form.selectGender(ConfigReader.get("gender"));
        form.selectHobby(ConfigReader.get("hobby"));

        form.addSubject(ConfigReader.get("subject"));

        form.selectDate(
                ConfigReader.get("birthMonth"),
                ConfigReader.get("birthYear"),
                ConfigReader.get("birthDay")
        );

        form.selectStateCity(
                ConfigReader.get("state"),
                ConfigReader.get("city")
        );

        form.submit();

        Assert.assertTrue(form.isSubmitted());
    }
}
