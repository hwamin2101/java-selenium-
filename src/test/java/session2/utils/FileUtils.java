package session2.utils;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {

    private static final String TEST_DIR = "test-files";
    private static final String DOWNLOAD_DIR = "downloads";

    public static File createTestFile(
            String name,
            String content
    ) {

        try {
            File folder = new File(TEST_DIR);
            if (!folder.exists()) folder.mkdir();

            File file = new File(folder, name);

            FileWriter writer =
                    new FileWriter(file);

            writer.write(content);
            writer.close();

            return file;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean waitForDownload(
            String filename,
            int seconds
    ) {

        Path path =
                Paths.get(DOWNLOAD_DIR, filename);

        for (int i = 0; i < seconds; i++) {

            if (Files.exists(path))
                return true;

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {}
        }

        return false;
    }

    public static long getFileSize(
            String filename
    ) throws Exception {

        Path path =
                Paths.get(DOWNLOAD_DIR, filename);

        return Files.size(path);
    }
}

