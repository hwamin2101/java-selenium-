package session3.exercise5_1.dataproviders;


import org.testng.annotations.DataProvider;
import session3.config.ConfigReader;
import session3.utils.*;

import java.util.List;

public class LoginDataProvider {

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String dataSource = ConfigReader.get("dataSource");
        String basePath = "src/test/resources/testdata/";

        List<String[]> data;

        switch (dataSource.toLowerCase()) {

            case "csv":
                data = CSVReaderUtil.readCSV(basePath + "login.csv");
                break;

            case "excel":
                data = ExcelReaderUtil.readExcel(
                        basePath + "login.xlsx",
                        "Sheet1");
                break;

            case "json":
                data = JsonReaderUtil.readJson(basePath + "login.json");
                break;

            default:
                throw new RuntimeException("Invalid data source!");
        }

        Object[][] result = new Object[data.size()][2];

        for (int i = 0; i < data.size(); i++) {
            result[i][0] = data.get(i)[0];
            result[i][1] = data.get(i)[1];
        }

        return result;
    }
}
