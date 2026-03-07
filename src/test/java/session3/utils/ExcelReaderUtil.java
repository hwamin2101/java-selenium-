package session3.utils;

import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelReaderUtil {

    public static List<String[]> readExcel(String filePath, String sheetName) {

        List<String[]> data = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheet(sheetName);

            for (int i = 1; i <= sheet.getLastRowNum(); i++) {

                Row row = sheet.getRow(i);

                String username = row.getCell(0).toString();
                String password = row.getCell(1).toString();

                data.add(new String[]{username, password});
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
