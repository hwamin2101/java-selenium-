package session3.utils;

import java.io.*;
import java.util.*;

public class CSVReaderUtil {

    public static List<String[]> readCSV(String filePath) {

        List<String[]> data = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(
                new FileReader(filePath))) {

            String line;
            boolean isHeader = true;

            while ((line = br.readLine()) != null) {

                if (isHeader) { // skip header
                    isHeader = false;
                    continue;
                }

                String[] values = line.split(",");
                data.add(values);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}