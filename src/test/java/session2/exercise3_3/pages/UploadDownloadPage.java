package session2.exercise3_3.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class UploadDownloadPage {

    private WebDriver driver;

    private By uploadInput =
            By.id("uploadFile");

    private By uploadedFileText =
            By.id("uploadedFilePath");

    private By downloadBtn =
            By.id("downloadButton");

    public UploadDownloadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void uploadFile(String path) {
        driver.findElement(uploadInput)
                .sendKeys(path);
    }

    public String getUploadedFileName() {
        return driver.findElement(uploadedFileText)
                .getText();
    }

    public void clickDownload() {

        WebDriverWait wait =
                new WebDriverWait(driver, Duration.ofSeconds(10));

        WebElement btn =
                wait.until(ExpectedConditions.elementToBeClickable(downloadBtn));

        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].click();", btn);
    }


}
