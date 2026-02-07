package session2.exercise3_3.tests;

import org.testng.Assert;
import org.testng.annotations.Test;
import session1.utils.ConfigReader;
import session2.base.BaseTest;
import session2.exercise3_3.pages.UploadDownloadPage;
import session2.utils.FileUtils;

import java.io.File;

public class UploadDownloadTest extends BaseTest {
    @Test
    public void testUploadAndDownload() throws Exception {

        driver.get(ConfigReader.get("file.url"));

        UploadDownloadPage page =
                new UploadDownloadPage(driver);

        // Dynamic file creation

        File file =
                FileUtils.createTestFile(
                        "upload-test.txt",
                        "Automation test file"
                );

        // Upload

        page.uploadFile(file.getAbsolutePath());

        Assert.assertTrue(
                page.getUploadedFileName()
                        .contains("upload-test.txt"),
                "Upload failed"
        );

        // Download

        page.clickDownload();

        String downloadName =
                "sampleFile.jpeg";

        boolean downloaded =
                FileUtils.waitForDownload(
                        downloadName,
                        10
                );

        Assert.assertTrue(
                downloaded,
                "Download failed"
        );

        long size =
                FileUtils.getFileSize(
                        downloadName
                );

        Assert.assertTrue(
                size > 0,
                "Downloaded file empty"
        );
    }

    // Advanced — multiple uploads

    @Test
    public void testMultipleUploads() {

        driver.get(ConfigReader.get("file.url"));

        UploadDownloadPage page =
                new UploadDownloadPage(driver);

        File f1 =
                FileUtils.createTestFile(
                        "file1.txt",
                        "data1"
                );

        File f2 =
                FileUtils.createTestFile(
                        "file2.txt",
                        "data2"
                );

        page.uploadFile(f1.getAbsolutePath());
        Assert.assertTrue(
                page.getUploadedFileName()
                        .contains("file1.txt")
        );

        page.uploadFile(f2.getAbsolutePath());
        Assert.assertTrue(
                page.getUploadedFileName()
                        .contains("file2.txt")
        );
    }
}
