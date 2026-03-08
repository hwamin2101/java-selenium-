package session3.exercise5_2.dataproviders;

import org.testng.annotations.DataProvider;
import session3.config.ConfigReader;
import session3.utils.CSVReaderUtil;
import session3.utils.ExcelReaderUtil;
import session3.utils.JsonReaderUtil;

import java.util.List;

public class LoginDataProvider {

    private static final String BASE_PATH =
            "src/test/resources/testdata/";

    @DataProvider(name = "loginData")
    public static Object[][] getLoginData() {

        String dataSource =
                ConfigReader.get("dataSource1");

        if (dataSource == null || dataSource.isEmpty()) {
            throw new RuntimeException("dataSource not defined in config.properties");
        }

        List<String[]> rawData;

        switch (dataSource.toLowerCase()) {

            case "csv":
                rawData = CSVReaderUtil.readCSV(
                        BASE_PATH + "login.csv");
                break;

            case "excel":
                rawData = ExcelReaderUtil.readExcel(
                        BASE_PATH + "login.xlsx",
                        "Sheet1");
                break;

            case "json":
                rawData = JsonReaderUtil.readJson(
                        BASE_PATH + "login.json");
                break;

            default:
                throw new RuntimeException(
                        "Invalid dataSource value: " + dataSource);
        }

        return convertToDataProviderFormat(rawData);
    }
    private static Object[][] convertToDataProviderFormat(
            List<String[]> rawData) {

        Object[][] result =
                new Object[rawData.size()][3];

        for (int i = 0; i < rawData.size(); i++) {

            String username = rawData.get(i)[0];
            String password = rawData.get(i)[1];
            boolean expected =
                    username.equals("Hwamin211")
                            && password.equals("Zhengpeng0706#");

            result[i][0] = username;
            result[i][1] = password;
            result[i][2] = expected;
        }

        return result;
    }
    private static Object[][] getFromExcel() {

        String path =
                "src/test/resources/testdata/login.xlsx";

        List<String[]> rawData =
                ExcelReaderUtil.readExcel(
                        path, "Sheet1"
                );

        Object[][] result =
                new Object[rawData.size()][3];

        for (int i = 0; i < rawData.size(); i++) {

            String username = rawData.get(i)[0];
            String password = rawData.get(i)[1];

            boolean expected =
                    !username.isEmpty()
                            && !password.isEmpty()
                            && username.equals("Hwamin211")
                            && password.equals("Zhengpeng0706#");

            result[i][0] = username;
            result[i][1] = password;
            result[i][2] = expected;
        }

        return result;
    }

    private static Object[][] getFromCsv() {
        return new Object[0][0];
    }

    private static Object[][] getFromJson() {
        return new Object[0][0];
    }
}