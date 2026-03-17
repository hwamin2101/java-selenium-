package session3.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class JsonReaderUtil {

    public static List<String[]> readJson(String filePath) {

        List<String[]> data = new ArrayList<>();

        try {
            ObjectMapper mapper = new ObjectMapper();

            List<Map<String, String>> list =
                    mapper.readValue(new File(filePath), List.class);

            for (Map<String, String> entry : list) {
                data.add(new String[]{
                        entry.get("username"),
                        entry.get("password")
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return data;
    }
}
